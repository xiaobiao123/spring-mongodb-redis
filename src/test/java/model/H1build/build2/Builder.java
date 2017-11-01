package model.H1build.build2;

abstract  class Builder {

    public abstract void BuildHead();

    public abstract void BuildBody();

    public abstract void BuildHand();

    public abstract void BuildFeet();

    public abstract Product GetResult();
};