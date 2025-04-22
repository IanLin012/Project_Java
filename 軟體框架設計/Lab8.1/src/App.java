import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


interface ButtonControlable {
    void turnOn();
    void turnOff();
}

class ButtonPanel extends JPanel implements ActionListener {
    private JButton onButton;
    private JButton offButton;

    private ButtonControlable device;

    public ButtonPanel() {
        onButton = new JButton("On");
        offButton = new JButton("Off");

        onButton.addActionListener(this);
        offButton.addActionListener(this);

        this.setLayout(new FlowLayout());
        this.add(onButton);
        this.add(offButton);
    }

    public void connect(ButtonControlable device) {
        this.device = device;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (device == null) {
            System.out.println("尚未連結任何裝置！");
            return;
        }
        if (e.getSource() == onButton) {
            device.turnOn();
        } else if (e.getSource() == offButton) {
            device.turnOff();
        }
    }
}

class Lamp implements ButtonControlable {
    private final String name;

    public Lamp(String name) {
        this.name = name;
    }

    @Override
    public void turnOn() {
        System.out.println(name + "燈已開啟");
    }

    @Override
    public void turnOff() {
        System.out.println(name + "燈已關閉");
    }
}

class Fan implements ButtonControlable {
    private final String name;

    public Fan(String name) {
        this.name = name;
    }

    @Override
    public void turnOn() {
        System.out.println(name + "電風扇開始運轉");
    }

    @Override
    public void turnOff() {
        System.out.println(name + "電風扇停止運轉");
    }
}

class Computer implements ButtonControlable {
    private final String name;

    public Computer(String name) {
        this.name = name;
    }

    @Override
    public void turnOn() {
        System.out.println(name + "電腦正在開機");
    }

    @Override
    public void turnOff() {
        System.out.println(name + "電腦正在關機");
    }
}

public class App {
    public static void main(String[] args) {
        JFrame frame = new JFrame("通用型開關器");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ButtonPanel panel = new ButtonPanel();

        Lamp lamp = new Lamp("客廳的");
        panel.connect(lamp);

        //Fan fan = new Fan("臥室的");
        //panel.connect(fan);

        //Computer computer = new Computer("書房的");
        //panel.connect(computer);

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

