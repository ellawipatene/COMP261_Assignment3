package comp261.assig3;

import javafx.geometry.Point2D;

// A helper class for projection of the graph to the canvas

public class Projection {
        // map model to screen using scale and origin passing in all variables needed
        public static Point2D model2Screen (Point2D model, GraphController gc) {
            double x = model2ScreenX(model.getX(), gc.mapCanvas.getWidth()/2.0, gc.scale, gc.origin.getX());
            double y = model2ScreenY(model.getY(), gc.mapCanvas.getHeight()/2.0, gc.scale, gc.origin.getY());
            return new Point2D(x,y);
        }
        public static double model2ScreenX (double modelX, double drawingCenter, double scale, double originX) {
            return (modelX - originX) * scale + drawingCenter; 
        }
        public static double model2ScreenY (double modelY, double drawingCenter, double scale, double originY) {
            return (modelY - originY)* scale + drawingCenter;
        }


        // map screen to model using scale and origin
        public static Point2D screen2Model (Point2D screen, GraphController gc) {
            double x = screen2ModelX(screen.getX(), gc.mapCanvas.getWidth()/2.0, gc.scale, gc.origin.getX());
            double y = screen2ModelY(screen.getY(), gc.mapCanvas.getHeight()/2.0, gc.scale, gc.origin.getY());
            return new Point2D(x,y);
        }
        public static double screen2ModelX(double screenPointX, double drawingCenter, double scale, double originX) {
            return (((screenPointX-drawingCenter)/scale) + originX);
        }
        public static double screen2ModelY(double screenPointY, double drawingCenter, double scale, double originY) {
            return (((screenPointY-drawingCenter)/scale) + originY);
        }
  
}
