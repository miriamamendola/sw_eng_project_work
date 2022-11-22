package project_work.model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Drawing implements Serializable, Iterable<Drawable>  {
    private List<Drawable> listDrawables;

    public Drawing(){
        this.listDrawables = new ArrayList<>();
    }

    public void addDrawable(Drawable drawable){
        this.listDrawables.add(drawable);
    }

    public Drawable getDrawable(int index){
        return this.listDrawables.get(index);
    }

    public Drawable removeDrawable(int index){
        return this.listDrawables.remove(index);
    }

    public boolean containsDrawable(Drawable drawable){
        return this.listDrawables.contains(drawable);
    }

    @Override
    public Iterator<Drawable> iterator() {
        return listDrawables.iterator();
    }
}
