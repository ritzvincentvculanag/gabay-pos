package me.jhayzonalbay.rmmcgabay.views.hero;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import me.jhayzonalbay.rmmcgabay.R;
import me.jhayzonalbay.rmmcgabay.models.Invoice;
import me.jhayzonalbay.rmmcgabay.models.PurchasedItem;
import me.jhayzonalbay.rmmcgabay.repositories.InvoiceRepository;


public class Dashboard extends Fragment {

    private TextView tfSales;
    private InvoiceRepository invoiceRepository;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        invoiceRepository = new InvoiceRepository(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_dashboard, container, false);
        tfSales = view.findViewById(R.id.tv_total_sale);

        Double sales = 0.0;

        for(Invoice item: invoiceRepository.getAll()){
            sales = sales + item.getSubTotal();
        }

        tfSales.setText(sales.toString());

        return  view;
    }
}