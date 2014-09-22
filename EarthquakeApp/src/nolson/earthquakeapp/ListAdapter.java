package nolson.earthquakeapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListAdapter extends ArrayAdapter<String[]>{
	private final Context context;
	private final String[][] values;
	
	public ListAdapter(Context context, String[][] values){
		super(context, R.layout.earthquake_list, values);
		this.context = context;
		this.values = values;
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	 
			View rowView = inflater.inflate(R.layout.earthquake_list, parent, false);
			TextView eqid = (TextView) rowView.findViewById(R.id.eqid);
			TextView date = (TextView) rowView.findViewById(R.id.date);
			TextView magnitude = (TextView) rowView.findViewById(R.id.magnitude);
			ImageView image = (ImageView) rowView.findViewById(R.id.image);
			//Set textViews
			eqid.setText("ID: " + values[position][0]);             //eqid
			date.setText("Date: " + values[position][1]);           //datetime
			magnitude.setText("Magnitude: " + values[position][2]); //magnitude
			//Set imageView
			if(Double.parseDouble(values[position][2]) >= 8)
				//If magnitude is greater than or equal to 8 set image to exclamation.jpg
				image.setImageResource(R.drawable.exclamation);
			else
				//Set image to eq.jpg (default image)
				image.setImageResource(R.drawable.eq);
						
			return rowView;
	}
}
