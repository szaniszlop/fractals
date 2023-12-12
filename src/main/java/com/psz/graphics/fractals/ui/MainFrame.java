package com.psz.graphics.fractals.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTextField;

import org.springframework.beans.BeansException;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class MainFrame extends JFrame implements ApplicationRunner, ApplicationContextAware{

    private ApplicationContext applicationContext;

	public MainFrame() {
        initUI();
    }

    private void initUI() {



        createLayout(titleText(), drawingBoard(), quitButton());

        setTitle("Fractals");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                
            }
        });
    }

    private void createLayout(JComponent... arg) {

        var pane = getContentPane();
        var bl = new BoxLayout(pane, BoxLayout.PAGE_AXIS);
        pane.setLayout(bl);
        for (JComponent component : arg){
            pane.add(component);
        }
    }    

    private JComponent titleText(){
        return new JTextField("Spring Boot can be used with Swing apps");
    }

    private JComponent drawingBoard(){
        return new MandebrotCanvasBuilder()
            .imageWidth(1200)
            .imageHeight(1200)
            .scaleBarOrientation(ColorScaleBar.Orientation.HORIZONTAL)
            .maxIterations(50)
            .topLeftReal(-2.0)
            .topLeftImaginary(0.5)
            .bottomRightReal(0.5)
            .bottomRightImaginary(-0.5)
            .build();
    }

    private JComponent quitButton(){
        var quitButton = new JButton("Quit");

        quitButton.addActionListener((ActionEvent event) -> {
            System.exit(0);
        });
        return quitButton;
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
    
    @Override
    public void run(ApplicationArguments args) throws Exception {
        EventQueue.invokeLater(() -> {
            var ex = applicationContext.getBean(MainFrame.class);
            ex.pack();
            ex.setVisible(true);
        });	
    }
}
