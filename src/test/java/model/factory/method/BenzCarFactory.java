package model.factory.method;

//奔驰车工厂
class BenzCarFactory extends CarFactory {
    public ICar createCar() {
        return new BenzCar();
    }
}  