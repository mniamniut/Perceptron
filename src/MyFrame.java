import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class MyFrame extends JFrame {
    private Perceptron perceptron= new Perceptron();

    private JPanel contentPane;
    private JPanel panel_1;
    private JPanel panel_2;
    private JPanel panel_3;
    private JPanel panel_4;
    private JPanel panel_5;
    private JTextField textField;


    private JPanel panelL1;
    private JPanel panelL2;
    private JLabel lblNewLabel;
    private JButton btnNewButton;
    private JButton btnNewButton_1;
    private JLabel lblNewLabel_1;


    public MyFrame(){


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("perceptron");
        setBounds(100, 100, 480, 300);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(187, 220, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

//========================================================================
        panel_1 = new JPanel();
        contentPane.add(panel_1);
        panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));


        btnNewButton_1 = new JButton("START");

        btnNewButton_1.addActionListener(e -> {


                try{
                    perceptron.learn();

                    lblNewLabel_1.setText("ACCURACY: "+ perceptron.accuracy+"%");




                }
                catch(Exception ex){
                    //System.out.println("podaj poprawne dane!");
                    lblNewLabel_1.setText("podaj poprawne dane");
            }

                }
        );
        panel_1.add(btnNewButton_1);
        contentPane.add(panel_1);
        //========================================================================

        panel_2 = new JPanel();
        lblNewLabel_1 = new JLabel("");
        panel_2.add(lblNewLabel_1);

        contentPane.add(panel_2);
        //========================================================================
        panel_3 = new JPanel();


        panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        textField = new JTextField();
        panel_3.add(new JLabel("ATTRIBUTES"));
        panel_3.add(textField);
        textField.setColumns(35);

/*
        textField_4 = new JTextField();
        panel_3.add(new JLabel("ACCURACY:"));
        panel_3.add(textField_4);
        textField_4.setColumns(5);
*/
        contentPane.add(panel_3);
        //========================================================================

        panel_4 = new JPanel();


        btnNewButton = new JButton("TEST");
        btnNewButton.addActionListener(e -> {
            try {
                String[] splitedText=textField.getText().split(":");
                ArrayList<Double> tmplist= new ArrayList<>();
                for(int i=0; i<splitedText.length;i++)
                    tmplist.add(Double.parseDouble(splitedText[i]));


                perceptron.getOutput(tmplist);

                if(perceptron.getOutput(tmplist)==0)
                    lblNewLabel.setText("classified to setosa");
                else
                    lblNewLabel.setText("classified to versicolor");
            }
            catch(Exception exc){
                //System.out.println("podaj poprawne dane!");
                lblNewLabel.setText("Dane nie wprowadzone, wczytywane sa dane z pliku testowego");
                Data testData= new Data("iristest.csv");

                for(Vector testVector : testData.vectorList){
                    if(perceptron.getOutput(testVector.attributes)==0)
                        System.out.println(testVector + " classified to setosa");
                    else
                        System.out.println(testVector +" classified to versicolor");
                }
            }




        });

        panel_4.add(btnNewButton);

        contentPane.add(panel_4);
//========================================================================
        panel_5 = new JPanel();
        lblNewLabel = new JLabel("");
        panel_5.add(lblNewLabel);

        contentPane.add(panel_5);
        setVisible(true);
    }
}
