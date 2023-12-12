package com.psz.graphics.fractals.ui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.text.DecimalFormat;

import com.psz.graphics.fractals.cnumbers.CNumber;

public class ComplexPlaneAxes {
    public static final int MIN_PIXELS_BETWEEN_TICKS = 30;
    public static final int BASE_TICK_SIZE = 2;
    public static final int MIDDLE_TICK_SIZE = 5;
    public static final int MAIN_TICK_SIZE = 10;

    public static final void paint(Graphics2D graphics, Color color, 
            int imageWidth, int imageHeight, 
            CNumber topLeft, CNumber bottomRight){
        double realStepPerPixel = ((bottomRight.getreal() - topLeft.getreal()) / imageWidth);
        double imaginaryStepPerPixel = ((topLeft.getImaginary() - bottomRight.getImaginary() ) / imageHeight);
        long originX = Math.round(Math.abs(topLeft.getreal()) / realStepPerPixel);
        long originY = Math.round(Math.abs(topLeft.getImaginary()) / imaginaryStepPerPixel);
        if(originX > 0 && originX < imageWidth && originY > 0 && originY < imageHeight) {
            int originXCoordinate = Long.valueOf(originX).intValue();
            int originYCoordinate = Long.valueOf(originY).intValue();
            graphics.setColor(color);
            graphics.drawLine(originXCoordinate, 0, originXCoordinate, imageHeight);
            graphics.drawLine(0, originYCoordinate, imageWidth, originYCoordinate);
            drawRealScale(graphics, color, imageWidth, 
                originXCoordinate, originYCoordinate,
                realStepPerPixel, topLeft, bottomRight);
            drawImaginaryScale(graphics, color, imageHeight, 
                originXCoordinate, originYCoordinate,
                imaginaryStepPerPixel, topLeft, bottomRight);
        }
    }

    private static final void drawRealScale(Graphics2D graphics, Color color, 
            int imageWidth, int originXCoordinate, int originYCoordinate,
            double stepPerPixel, CNumber topLeft, CNumber bottomRight) {
        drawScale(graphics, color, 
            imageWidth, originXCoordinate, originYCoordinate,
            stepPerPixel, bottomRight.getreal() - topLeft.getreal(),
            true);                
    }

    private static final void drawImaginaryScale(Graphics2D graphics, Color color, 
            int imageHeight, int originXCoordinate, int originYCoordinate,
            double stepPerPixel, CNumber topLeft, CNumber bottomRight) {
        drawScale(graphics, color, 
            imageHeight, originXCoordinate, originYCoordinate,
            stepPerPixel, topLeft.getImaginary() - bottomRight.getImaginary(),
            false);
    }    

    private static final void drawScale(Graphics2D graphics, Color color, 
            int imageSize, int originXCoordinate, int originYCoordinate,
            double stepPerPixel, double visibleRealAxisLength,
            boolean horizontal) {
        DecimalFormat decimalFormat = new DecimalFormat("####0.00");
        int maxTicks = imageSize / MIN_PIXELS_BETWEEN_TICKS;
        double minTicksDistance = visibleRealAxisLength / maxTicks;
        double mainAxisStep = Math.pow(10, Math.round(Math.log10(minTicksDistance)));
        double tickPosition = 0;
        int pixelsFromOrigin = Long.valueOf(Math.round(tickPosition / stepPerPixel)).intValue();
        int tickCounter = 0;
        while(tickInsideViewArea(pixelsFromOrigin, imageSize, (horizontal) ? originXCoordinate : originYCoordinate)){
            int tickerSizePixels = BASE_TICK_SIZE;
            String caption = "";
            tickerSizePixels = (tickCounter % 10) == 5 ? MIDDLE_TICK_SIZE : tickerSizePixels;
            tickerSizePixels = (tickCounter % 10) == 0 ? MAIN_TICK_SIZE : tickerSizePixels;
            caption = tickCounter > 0 && (tickCounter % 10) == 0 ?  decimalFormat.format(tickPosition)  : "";
            drawTick(graphics, color, imageSize, originXCoordinate, originYCoordinate, pixelsFromOrigin, tickerSizePixels, caption, horizontal);
            tickCounter++;
            tickPosition = tickPosition + mainAxisStep;
            pixelsFromOrigin = Long.valueOf(Math.round(tickPosition / stepPerPixel)).intValue();
        }
    }    
    
    private static final boolean tickInsideViewArea(int pixelsFromOrigin, int imageSize, int originCoordinate){
        return (originCoordinate + pixelsFromOrigin < imageSize) || (originCoordinate - pixelsFromOrigin > 0);
    }

    private static final void drawTick(Graphics2D graphics, Color color, 
            int imageSize, int originXCoordinate, int originYCoordinate,
            int pixelsFromOrigin, int tickerSizePixels,
            String caption, boolean horizontalAxis){
        int originCoordinate = horizontalAxis ? originXCoordinate : originYCoordinate;
        boolean drawCaption = !"".equals(caption);
        if(originCoordinate + pixelsFromOrigin < imageSize){
            if(horizontalAxis){
                graphics.drawLine(originXCoordinate + pixelsFromOrigin, originYCoordinate - tickerSizePixels, 
                    originXCoordinate + pixelsFromOrigin, originYCoordinate + tickerSizePixels);
                if(drawCaption){      
                    graphics.drawString(caption, originXCoordinate + pixelsFromOrigin - MIN_PIXELS_BETWEEN_TICKS / 2, originYCoordinate + MIN_PIXELS_BETWEEN_TICKS);    
                }
            }
            else {
                graphics.drawLine(originXCoordinate - tickerSizePixels, originYCoordinate + pixelsFromOrigin, 
                    originXCoordinate + tickerSizePixels, originYCoordinate + pixelsFromOrigin);
                if(drawCaption){    
                    graphics.drawString("-" + caption, originXCoordinate - MIN_PIXELS_BETWEEN_TICKS, originYCoordinate + pixelsFromOrigin + MIN_PIXELS_BETWEEN_TICKS / 2);       
                }
            }
        }
        if(originCoordinate - pixelsFromOrigin > 0){
            if(horizontalAxis){
                graphics.drawLine(originXCoordinate - pixelsFromOrigin, originYCoordinate - tickerSizePixels, 
                    originXCoordinate - pixelsFromOrigin, originYCoordinate + tickerSizePixels);
                if(drawCaption){
                    graphics.drawString("-" + caption, originXCoordinate - pixelsFromOrigin - MIN_PIXELS_BETWEEN_TICKS / 2, originYCoordinate + MIN_PIXELS_BETWEEN_TICKS);
                }                       
            }
            else {
                graphics.drawLine(originXCoordinate - tickerSizePixels, originYCoordinate - pixelsFromOrigin, 
                    originXCoordinate + tickerSizePixels, originYCoordinate - pixelsFromOrigin);
                if(drawCaption){      
                    graphics.drawString(caption, originXCoordinate - MIN_PIXELS_BETWEEN_TICKS, originYCoordinate - pixelsFromOrigin + MIN_PIXELS_BETWEEN_TICKS / 2);     
                }
            }
        }
    }

 }
