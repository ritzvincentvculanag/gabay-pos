package me.jhayzonalbay.rmmcgabay.actions;

import android.content.Context;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCaller;
import androidx.activity.result.ActivityResultLauncher;

import com.google.android.material.textfield.TextInputLayout;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanIntentResult;
import com.journeyapps.barcodescanner.ScanOptions;

import me.jhayzonalbay.rmmcgabay.models.Invoice;
import me.jhayzonalbay.rmmcgabay.models.Product;
import me.jhayzonalbay.rmmcgabay.repositories.ProductRepository;
import me.jhayzonalbay.rmmcgabay.utils.Executable;

public class BarcodeScanner implements Executable {

    private Context context;
    private ActivityResultCaller caller;
    private ActivityResultLauncher<ScanOptions> launcher;

    private Invoice invoice;
    private ProductRepository productRepository;
    private TextInputLayout tfBarCode;
    private Button viewCart;

    public BarcodeScanner(Context context, ActivityResultCaller caller, Invoice invoice, Button viewCart) {
        this.context = context;
        this.caller = caller;
        this.productRepository = new ProductRepository(context);
        this.invoice = invoice;
        this.viewCart = viewCart;
        launcher = caller.registerForActivityResult(new ScanContract(), this::launcherResult);
    }

    public BarcodeScanner(Context context, ActivityResultCaller caller, TextInputLayout tvBarCode) {
        this.context = context;
        this.caller = caller;
        this.productRepository = new ProductRepository(context);
        this.tfBarCode = tvBarCode;
        launcher = caller.registerForActivityResult(new ScanContract(), this::launcherAddTextResult);
    }

    @Override
    public void execute() {
        ScanOptions options = new ScanOptions();

        options.setPrompt("Press volume up to turn on flash");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(BarcodeCapture.class);

        launcher.launch(options);
    }

    private void launcherResult(ScanIntentResult result) {
        if (result.getContents() == null) {
            return;
        }

        Product productToAdd = null;

        for (Product product : productRepository.getAll()) {
            if (product.getBarcode().equals(result.getContents())) {
                productToAdd = product;
            }
        }
        if (productToAdd != null) {
            if (invoice.getProducts().contains(productToAdd)) {
                Toast.makeText(context, "Product is already in cart!", Toast.LENGTH_SHORT).show();
                return;
            }
            invoice.setSubTotal(invoice.getSubTotal() + productToAdd.getPrice() * productToAdd.getQuantity());
            invoice.addProduct(productToAdd);
            viewCart.setText(String.valueOf(invoice.getProducts().size()));
        } else {
            Toast.makeText(context, "Product is not exist!", Toast.LENGTH_SHORT).show();
        }
    }

    private void launcherAddTextResult(ScanIntentResult result) {
        if (result.getContents() == null) {
            return;
        }
        tfBarCode.getEditText().setText(result.getContents());
    }

}
