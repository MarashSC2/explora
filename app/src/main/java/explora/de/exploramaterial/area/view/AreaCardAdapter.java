package explora.de.exploramaterial.area.view;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import explora.de.exploramaterial.R;
import explora.de.exploramaterial.address.entity.Address;

/**
 * Created by Marash on 25.05.2016.
 */

/**
 * Implementiert die Klick-Logik für eine ausgewählte Area (z.B.Berlin).
 */
public class AreaCardAdapter extends ArrayAdapter<Address> {
    private Context context;
    private List<Address> addresses;
    private AreaCardClickListener cardClickListener;

    public AreaCardAdapter(Context context, List<Address> addresses, AreaCardClickListener cardClickListener) {
        super(context, -1, addresses);
        this.context = context;
        this.addresses = addresses;
        this.cardClickListener = cardClickListener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final int id = position;

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.city_cardlist_layout, parent, false);

        final TextView textView = (TextView) rowView.findViewById(R.id.info_text);
        textView.setText(this.addresses.get(position).getCity());

        final CardView cardView = (CardView) rowView.findViewById(R.id.card_view);
        cardView.setCardBackgroundColor(context.getResources().getColor(R.color.colorPrimary));

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardClickListener.onAreaCardClick(addresses.get(id));
            }
        });

        return rowView;
    }
}
