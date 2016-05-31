package explora.de.exploramaterial.tour.view;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import explora.de.exploramaterial.tour.entity.Tour;
import explora.de.exploramaterial.R;

/**
 * Created by Andi on 25.05.2016.
 */
public class TourCardAdapter extends ArrayAdapter<Tour> {

    private Context context;
    private List<Tour> tours;
    private TourCardClickListener tourCardClickListener;

    public TourCardAdapter(Context context, List<Tour> tours, TourCardClickListener tourCardClickListener) {
        super(context, -1, tours);
        this.context = context;
        this.tours = tours;
        this.tourCardClickListener = tourCardClickListener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final int id = position;

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.city_cardlist_layout, parent, false);

        final TextView textView = (TextView) rowView.findViewById(R.id.info_text);
        textView.setText(this.tours.get(position).getTitle());

        final CardView cardView = (CardView) rowView.findViewById(R.id.card_view);
        cardView.setCardBackgroundColor(context.getResources().getColor(R.color.cardview_light_background));

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tourCardClickListener.onTourCardClick(tours.get(id));
            }
        });

        return rowView;
    }
}
