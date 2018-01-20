package cn.com.greattimes.support.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import org.slf4j.Logger;

import cn.com.greattimes.support.log.LogBack;
import cn.com.greattimes.support.tuxedo.SnmpManager;
import cn.com.greattimes.support.tuxedo.SnmpRequest;
import cn.com.greattimes.support.tuxedo.SnmpResponse;

public class Operation implements Callable<Map<String, String>> {
	
	static Logger logger = LogBack.getLogger("snmp");
	
    /**
     * The SNMP manager to use.
     */
    private SnmpManager manager;

    /**
     * The SNMP request object.
     */
    private SnmpRequest request;

    
    public Operation(SnmpManager manager, SnmpRequest request) {

        this.manager = manager;
        this.request = request;
    }

   
    Map<String, String> start() {
    	//FutureTask一个可取消的异步计算，FutureTask 实现了Future的基本方法，提空 start cancel 操作，可以查询计算是否已经完成，
    	//并且可以获取计算的结果。结果只可以在计算完成之后获取，get方法会阻塞当计算没有完成的时候，一旦计算已经完成，那么计算就不能再次启动或是取消。
    	//一个FutureTask 可以用来包装一个 Callable 或是一个runnable对象。因为FurtureTask实现了Runnable方法，所以一个 
    	//FutureTask可以提交(submit)给一个Excutor执行(excution).
    	FutureTask<Map<String, String>> futureTask = new FutureTask<>(this);//this 即Operation对象
    	new Thread(futureTask).start();
    	
    	Map<String, String> map = null;
    	try {
			map = futureTask.get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
    	
    }

    /**
     * Stops the background thread.
     */
    
    @Override
	public Map<String, String> call() throws Exception {
    	Map<String, String> runGetAll = null;
    	 try {
    		 runGetAll = runGetAll();
         } catch (Exception e) {
         	logger.error("Error: "+e.getMessage()+"\n");
         }
         manager.destroy();
		 return runGetAll;
	}

    private Map<String, String> runGetAll() {
        String oid = request.getOid();
        boolean stopped = false;
      //将获取的数据信息   存放到map集合中key为OID   value为获取的信息
		Map<String, String> map = new HashMap<String, String>();
        do {
            SnmpResponse response = null;
			try {
//				manager.get(oids)
				//setNowTime();
				response = manager.getNext(oid);
				//System.out.println(response.getOidsAndValues());
				
			} catch (Exception e) {
				e.printStackTrace();
			}
            oid = response.getOid(0);
            String value = response.getValue(0);
            
//            System.out.println(oid+"--->"+value);
            
            map.put(oid, value);
//            System.out.println( oid + ":\t"+ value);
//            logger.error("oid:{},--->,value:{}",oid.toString(),value.toString());
            if (!oid.startsWith(request.getOid())) {
              stopped = true;
            }
        } while (!stopped);
		return map;
    }
}