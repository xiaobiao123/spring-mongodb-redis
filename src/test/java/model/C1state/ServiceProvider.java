package model.C1state;

//服务提供者
class ServiceProvider{  
    private State state;  
    public ServiceProvider(State state){  
         this.state = state;  
    }  
    //状态更改  
    public void changeState(State newState){  
         state = newState;  
    }  
    public void service1(){  
          //……  
          state.operation1();  
          //……  
          state.operation3();  
    }  
    public void service2(){  
          //……  
          state.operation1();  
          //……  
          state.operation2();  
    }  
    public void service3(){  
          //……  
          state.operation3();  
          //……  
          state.operation2();  
    }  
}  