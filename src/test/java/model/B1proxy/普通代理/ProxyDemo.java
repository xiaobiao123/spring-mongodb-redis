package model.B1proxy.普通代理;


//代理程序

//客户端程序调用代理
public class ProxyDemo{
	public static void main(String[] args){
		//客户端调用代理程序
		Proxy p = new Proxy();
		p.f();
		p.g();
		p.h();
	}
}