package me.jhayzonalbay.rmmcgabay.actions;

import android.content.Context;

import androidx.activity.result.ActivityResultCaller;
import androidx.activity.result.ActivityResultLauncher;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanIntentResult;
import com.journeyapps.barcodescanner.ScanOptions;

import me.jhayzonalbay.rmmcgabay.utils.Executable;

public class BarcodeScanner implements Executable {

    private Context context;
    private ActivityResultCaller caller;
    private ActivityResultLauncher<ScanOptions> launcher;

    public BarcodeScanner(Context context, ActivityResultCaller caller) {
        this.context = context;
        this.caller = caller;

        launcher = caller.registerForActivityResult(new ScanContract(), this::launcherResult);
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

        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(context);

        builder.setTitle("Result");
        builder.setMessage(result.getContents());
        builder.setPositiveButton("Ok", (dialog, which) -> {});
        builder.show();
    }

}
