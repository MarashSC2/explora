package explora.de.exploramaterial.city.view;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import explora.de.exploramaterial.R;

/**
 * Created by Marash on 25.05.2016.
 */
public class CityCardAdapter extends ArrayAdapter<String> {

    private Context context;
    private String[] values;
    private CityCardClickListener cardClickListener;

    public CityCardAdapter(Context context, String[] values, CityCardClickListener cardClickListener) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
        this.cardClickListener = cardClickListener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final int id = position;

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.city_cardlist_layout, parent, false);

        final TextView textView = (TextView) rowView.findViewById(R.id.info_text);
        textView.setText(values[position]);

        final CardView cardView = (CardView) rowView.findViewById(R.id.card_view);
        cardView.setCardBackgroundColor(context.getResources().getColor(R.color.colorPrimary));

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardClickListener.onCityCardClick(id);
            }
        });

        return rowView;
    }
}
