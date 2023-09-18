import java.awt.*;
import java.util.ArrayList;

public class NestedShape extends RectangleShape{
    private ArrayList<Shape> innerShapes = new ArrayList<>();

    public NestedShape() {
        super();
        createInnerShape(0, 0, width/2, height/2, color, PathType.BOUNCE, text, ShapeType.RECTANGLE);
    }

    public NestedShape(int x, int y, int w, int h, int mw, int mh, Color c, PathType pt, String t) {
        super(x ,y, w, h, mw, mh, c, pt, t);
        createInnerShape(0, 0, width/2, height/2, color, PathType.BOUNCE, t, ShapeType.RECTANGLE);
    }

    public NestedShape(int w, int h) {
        super(0, 0, w, h, DEFAULT_MARGIN_WIDTH, DEFAULT_MARGIN_HEIGHT, Color.black, PathType.BOUNCE, "");
    }

    public Shape createInnerShape(int x, int y, int w, int h, Color c, PathType pt, String t, ShapeType st) {
        Shape innerShape;
        switch (st) {
            case RECTANGLE: {
                innerShape = new RectangleShape(x, y, w, h, width, height, c, pt, t);
                innerShapes.add(innerShape);
                innerShape.setParent(this);
                break;
            } case OVAL: {
                innerShape = new OvalShape(x, y, w, h, width, height, c, pt, t);
                innerShapes.add(innerShape);
                innerShape.setParent(this);
                break;
            }
            default: {
                innerShape = new NestedShape(x, y, w, h, width, height, c, pt, t);
                innerShapes.add(innerShape);
                innerShape.setParent(this);
            }
        }
        return innerShape;
    }

    public Shape getInnerShapeAt(int index) {
        return innerShapes.get(index);
    }

    public int getSize() {
        return innerShapes.size();
    }


    // Question 4
    public int indexOf(Shape s) {
        return innerShapes.indexOf(s);
    }

    public void add(Shape s) {
        innerShapes.add(s);
        s.setParent(this);
    }

    public void remove(Shape s) {
        innerShapes.remove(s);
        s.setParent(null);
    }

    public ArrayList<Shape> getAllInnerShapes() {
        return innerShapes;
    }


    // Question 5
    public void setWidth(int w) {
        width = w;
        for (Shape shape: innerShapes) {
            shape.marginWidth = w;
        }
    }

    public void setHeight(int h) {
        height = h;
        for (Shape shape: innerShapes) {
            shape.marginHeight = h;
        }
    }

    public void setColor(Color c) {
        color = c;
        for (Shape shape: innerShapes) {
            shape.setColor(c);
        }
    }

    public void setText(String t) {
        text = t;
        for (Shape shape: innerShapes) {
            shape.setText(t);
        }
    }


    // Question 7
    public void draw(Painter painter) {
        painter.setPaint(Color.black);
        painter.drawRect(x, y, width, height);
        painter.translate(x, y);
        for (Shape shape: innerShapes) {
            shape.draw(painter);
        }
        painter.translate(-x, -y);
    }

    public void move() {
        super.move();
        for (Shape shape: innerShapes) {
            shape.move();
        }
    }


}
