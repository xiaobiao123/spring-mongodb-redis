package model.F6factory.method;

//宝马车工厂
class BMWCarFactory extends CarFactory {
    public ICar createCar() {
        return new BMWCar();
    }
}  