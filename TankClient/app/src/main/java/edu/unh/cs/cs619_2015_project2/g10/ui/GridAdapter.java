package edu.unh.cs.cs619_2015_project2.g10.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.SystemService;
import com.squareup.picasso.Picasso;
import edu.unh.cs.cs619_2015_project2.g10.R;
import java.util.Observable;
import java.util.Observer;

/**
 * Grid adapter updates the grid for the client.
 */
@EBean
public class GridAdapter extends BaseAdapter implements Observer {

    private final Object monitor = new Object();

    @SystemService
    protected LayoutInflater inflater;

    protected Context mContext;
    private int[][] mEntities = new int[16][16];

    public GridAdapter( Context context ){
        mContext = context;
    }

    @Override
    public void update( Observable observer, Object object ){
        updateList( (int[][]) object );
    }

    public void updateList(int[][] entities) {
        synchronized (monitor) {
            this.mEntities = entities;
            this.notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return 16 * 16;
    }

    @Override
    public Object getItem(int position) {
        return mEntities[(int) position / 16][position % 16];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.field_item, null);
        }

        int row = position / 16;
        int col = position % 16;

        int val = mEntities[row][col];

        if (convertView instanceof ImageView) {
            synchronized (monitor) {
                if (val > 0) {

                    if (val == 1000) {
                        Picasso.with(mContext)
                                .load("http://findicons.com/files/icons/1681/siena/256/wall_red.png")
                                .resize( 50, 50 )
                                .into((ImageView) convertView);
                    } else if (val >= 2000000 && val <= 3000000) {
                        Picasso.with(mContext)
                                .load("http://piq.codeus.net/static/media/userpics/piq_42023_400x400.png")
                                .resize(50, 50)
                                .into((ImageView) convertView);
                    } else if (val >= 10000000 && val <= 20000000) {
                        String stringID = Integer.toString(val);
                        int direction = Integer.parseInt(stringID.substring( 7));
                        int rotate = 0;
                        if( direction == 2 )
                            rotate = 90;
                        else if( direction == 4 )
                            rotate = 180;
                        else if( direction == 6 )
                            rotate = 270;

                        Picasso.with(mContext)
                                .load(R.drawable.blue_tank)
                                .resize(50, 50)
                                .rotate( rotate )
                                .into((ImageView) convertView);
                    } else {
                        Picasso.with(mContext)
                                .load("http://www.johnsusek.com/projects/textures/lawdogs/ground_grass_1024_tile.jpg")
                                .resize( 50, 50 )
                                .into((ImageView) convertView);
                    }
                }
                else{
                    Picasso.with(mContext)
                            .load("http://www.johnsusek.com/projects/textures/lawdogs/ground_grass_1024_tile.jpg")
                            .resize( 50, 50 )
                            .into((ImageView) convertView);
                }
            }
        }
        return convertView;
    }
}


