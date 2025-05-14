// 運動鞋
class SportShoesBody implements ShoesBody {
    public void create() {
        System.out.println("Creating Sport Shoes Body");
    }
}
class SportShoesStrap implements ShoesStrap {
    public void create() {
        System.out.println("Creating Sport Shoes Strap");
    }
}
class SportShoesBottom implements ShoesBottom {
    public void create() {
        System.out.println("Creating Sport Shoes Bottom");
    }
}
class SportShoesFactory implements ShoesFactory {
    public ShoesBody createBody() {
        return new SportShoesBody();
    }
    public ShoesStrap createStrap() {
        return new SportShoesStrap();
    }
    public ShoesBottom createBottom() {
        return new SportShoesBottom();
    }
}

// 休閒鞋
class LeisureShoesBody implements ShoesBody {
    public void create() {
        System.out.println("Creating Leisure Shoes Body");
    }
}
class LeisureShoesStrap implements ShoesStrap {
    public void create() {
        System.out.println("Creating Leisure Shoes Strap");
    }
}
class LeisureShoesBottom implements ShoesBottom {
    public void create() {
        System.out.println("Creating Leisure Shoes Bottom");
    }
}
class LeisureShoesFactory implements ShoesFactory {
    public ShoesBody createBody() {
        return new LeisureShoesBody();
    }

    public ShoesStrap createStrap() {
        return new LeisureShoesStrap();
    }

    public ShoesBottom createBottom() {
        return new LeisureShoesBottom();
    }
}

// 皮鞋
class LeatherShoesBody implements ShoesBody {
    public void create() {
        System.out.println("Creating Leather Shoes Body");
    }
}

class LeatherShoesStrap implements ShoesStrap {
    public void create() {
        System.out.println("Creating Leather Shoes Strap");
    }
}

class LeatherShoesBottom implements ShoesBottom {
    public void create() {
        System.out.println("Creating Leather Shoes Bottom");
    }
}

class LeatherShoesFactory implements ShoesFactory {
    public ShoesBody createBody() {
        return new LeatherShoesBody();
    }

    public ShoesStrap createStrap() {
        return new LeatherShoesStrap();
    }

    public ShoesBottom createBottom() {
        return new LeatherShoesBottom();
    }
}

class Shoes {
    private ShoesBody body;
    private ShoesStrap strap;
    private ShoesBottom bottom;

    public void makeShoes(ShoesFactory factory) {
        body = factory.createBody();
        strap = factory.createStrap();
        bottom = factory.createBottom();

        body.create();
        strap.create();
        bottom.create();

        System.out.println("Shoes assembly complete.\n");
    }
}