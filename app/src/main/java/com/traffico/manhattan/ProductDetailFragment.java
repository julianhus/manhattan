package com.traffico.manhattan;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.traffico.manhattan.classes.ProductContent;
import com.traffico.manhattan.entities.Producto;
import com.traffico.manhattan.entities.Tienda;
import com.traffico.manhattan.entities.ValorProducto;

import java.util.ArrayList;

/**
 * A fragment representing a single Product detail screen.
 * This fragment is either contained in a {@link ProductListActivity}
 * in two-pane mode (on tablets) or a {@link ProductDetailActivity}
 * on handsets.
 */
public class ProductDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "product_id";


    /**
     * The dummy content this fragment is presenting.
     */
    //private DummyContent.DummyItem mItem;
    private Producto producto;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ProductDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            //producto = ProductContent.PRODUCTO_MAP.get(getArguments().getString(ARG_ITEM_ID));
            producto = ProductContent.PRODUCTO_MAP.get(getArguments().getInt(ARG_ITEM_ID));

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(producto.getMarca() + "/" + producto.getDescProducto());

            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.product_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (producto != null) {
            //((TextView) rootView.findViewById(R.id.product_detail)).setText(mItem.details);
            ((TextView) rootView.findViewById(R.id.txtmarca)).setText(producto.getMarca());
            ((TextView) rootView.findViewById(R.id.txtdesc)).setText(producto.getDescProducto());
            ((TextView) rootView.findViewById(R.id.txtbarcode)).setText(producto.getBarCode());
            //
            ArrayList<ValorProducto> lValorProducto = (ArrayList<ValorProducto>) producto.getValorProductos();
            ArrayAdapter<ValorProducto> aValorProducto = new ArrayAdapter<ValorProducto>(this.getContext(), android.R.layout.simple_list_item_1, lValorProducto);
            ListView lvValorProducto  = (ListView)rootView.findViewById(R.id.lvValorProducto);
            lvValorProducto.setAdapter(aValorProducto);
            //
        }
        return rootView;
    }
}
