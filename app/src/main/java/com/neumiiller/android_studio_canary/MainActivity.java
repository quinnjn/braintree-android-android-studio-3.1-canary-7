package com.neumiiller.android_studio_canary;

import android.app.AlertDialog;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.braintreepayments.api.BraintreeFragment;
import com.braintreepayments.api.Card;
import com.braintreepayments.api.exceptions.InvalidArgumentException;
import com.braintreepayments.api.interfaces.BraintreePaymentResultListener;
import com.braintreepayments.api.interfaces.PaymentMethodNonceCreatedListener;
import com.braintreepayments.api.models.BraintreePaymentResult;
import com.braintreepayments.api.models.CardBuilder;
import com.braintreepayments.api.models.PaymentMethodNonce;

public class MainActivity extends AppCompatActivity implements PaymentMethodNonceCreatedListener {

    private BraintreeFragment mBraintreeFragment;
    private String mBraintreeSandboxTokenizationKey = "sandbox_tmxhyf7d_dcpspy2brwdjr3qn";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            mBraintreeFragment = BraintreeFragment.newInstance(this, mBraintreeSandboxTokenizationKey);
        } catch(InvalidArgumentException invalidArgumentException) {
            throw new RuntimeException(invalidArgumentException);
        }

        CardBuilder cardBuilder = new CardBuilder()
                .cardNumber("4111111111111111")
                .expirationDate("02/18");

        Card.tokenize(mBraintreeFragment, cardBuilder);
    }

    @Override
    public void onPaymentMethodNonceCreated(PaymentMethodNonce paymentMethodNonce) {
        Toast.makeText(this, paymentMethodNonce.getNonce(), Toast.LENGTH_SHORT)
                .show();
    }
}
