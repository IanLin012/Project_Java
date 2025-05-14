// 鞋身
interface ShoesBody {
    void create();
}

// 鞋帶
interface ShoesStrap {
    void create();
}

// 鞋底
interface ShoesBottom {
    void create();
}

interface ShoesFactory {
    ShoesBody createBody();
    ShoesStrap createStrap();
    ShoesBottom createBottom();
}