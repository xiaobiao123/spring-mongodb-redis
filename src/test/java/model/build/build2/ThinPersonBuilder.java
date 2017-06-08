package model.build.build2;


//具体廋人创建类
class ThinPersonBuilder extends Builder {

    private Product product=new Product();

    @Override
    public void BuildHead() {
        product.Add("廋人头");//创建瘦人的头

    }

    @Override
    public void BuildBody() {
        product.Add("廋人身体");//创建瘦人的身体
    }

    @Override
    public void BuildHand() {
        product.Add("廋人手");//创建瘦人的手
    }

    @Override
    public void BuildFeet() {
        product.Add("廋人脚");//创建瘦人的脚
    }

    @Override
    public Product GetResult() {
        return product;
    }
};