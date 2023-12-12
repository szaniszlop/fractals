package com.psz.graphics.fractals.ui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import com.psz.graphics.fractals.cnumbers.CNumber;
import com.psz.graphics.fractals.compute.DataPlane;
import com.psz.graphics.fractals.compute.MandebrotSetCalculator;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class MandebrotCanvas extends BaseCanvas{

    private final MandebrotSetCalculator calculator;
    private final ColorScaleBar.Orientation orientation;
    private CNumber topLeft, bottomRight;
    private boolean calculated = false;
    private BufferedImage image;
    private Color[] palette;

    public MandebrotCanvas(int imageWidth, int imageHeight, 
                            CNumber z, double convergenceThreshold, double divergenceThreshold, 
                            int maxIterations, CNumber topLeft, CNumber bottomRight,
                            ColorScaleBar.Orientation orientation) {
        super(imageWidth, imageHeight);
        this.orientation = orientation;
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
        calculator = new MandebrotSetCalculator(
            z, convergenceThreshold, divergenceThreshold, maxIterations, 
            topLeft, bottomRight, 
            imageWidth, imageHeight);
        initPalette(maxIterations);
    }



    @Override
    protected void paint(Graphics2D g) {
        log.info("paint method called");
        if(!calculated){
            log.info("painting the image for veri first time");
            DataPlane<Integer> result = calculator.calculate();
            image = result.toStream().collect(new BufferedImageCollector( getWidth(), getHeight(), palette) );
            calculated = true;
        }
        g.drawImage(image, null, 0, 0);
        ComplexPlaneAxes.paint(g, Color.RED, getWidth(), getHeight(), getTopLeft(), getBottomRight());
        ColorScaleBar.paint(g, palette, orientation, getWidth(), getHeight());
    }

    private void initPalette(int shades){
        assert shades <= 256;
        this.palette = new Color[shades];
        int step = 256/shades;
        for( int index = 0; index < shades ; index++){
            palette[index] = new Color(index * step, index * step, index * step);
        }
    }

    @Slf4j
    private static final class BufferedImageCollector implements Collector<Integer, BufferedImage, BufferedImage>{

        private final BufferedImage imageBuffer;
        private final int imageWidth;
        private int counter;
        private final Color[] palette;

        protected BufferedImageCollector(int imageWidth, int imageHeight, Color[] palette){
            this.imageWidth = imageWidth;
            this.counter = 0;
            this.palette = palette;
            this.imageBuffer = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_BYTE_INDEXED);

            log.info("Buffered created");
        }

        @Override
        public Supplier<BufferedImage> supplier() {
            return new Supplier<BufferedImage>() {
                @Override
                public BufferedImage get(){
                    return imageBuffer;
                }
            };
        }

        @Override
        public BiConsumer<BufferedImage, Integer> accumulator() {
            return new BiConsumer<BufferedImage, Integer>(){

                @Override
                public void accept(BufferedImage image, Integer value) {
                    image.setRGB(counter % imageWidth, counter / imageWidth, palette[ value % palette.length ].getRGB());
                    counter++;
                }
                
            };
        }

        @Override
        public BinaryOperator<BufferedImage> combiner() {
            return new BinaryOperator<BufferedImage>(){

                @Override
                public BufferedImage apply(BufferedImage t, BufferedImage u) {
                    // this collector does not support parallel execution
                    throw new UnsupportedOperationException("Unimplemented method 'apply'");
                }
                
            };
        }

        @Override
        public Function<BufferedImage, BufferedImage> finisher() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'finisher'");
        }

        @Override
        public Set<Characteristics> characteristics() {
            return Set.of(Characteristics.IDENTITY_FINISH);
        }

 
        
    }
    
}
