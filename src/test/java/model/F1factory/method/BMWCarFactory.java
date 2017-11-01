package model.F1factory.method;

//宝马车工厂
class BMWCarFactory extends CarFactory {
    public ICar createCar() {
        return new BMWCar();
    }
}  