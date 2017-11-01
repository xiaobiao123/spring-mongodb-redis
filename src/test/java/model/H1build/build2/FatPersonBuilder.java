package model.H1build.build2;


//具体胖人创建类
class FatPersonBuilder extends Builder {

    private Product product=new Product();

    @Override
    public void BuildHead() {
        product.Add("胖人头");//创建瘦人的头

    }

    @Override
    public void BuildBody() {
        product.Add("胖人身体");//创建瘦人的身体
    }

    @Override
    public void BuildHand() {
        product.Add("胖人手");//创建瘦人的手
    }

    @Override
    public void BuildFeet() {
        product.Add("胖人脚");//创建瘦人的脚
    }

    @Override
    public Product GetResult() {
        return product;
    }
};