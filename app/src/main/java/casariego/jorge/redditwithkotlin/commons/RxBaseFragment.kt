package casariego.jorge.redditwithkotlin.commons

import android.support.v4.app.Fragment
import rx.subscriptions.CompositeSubscription

/**
 * Created by jorgecasariego on 16/8/17.
 *
 * Explicación CompositeSubscription: http://sglora.com/android-tutorial-sobre-rxjava-vi/
 */
open class RxBaseFragment() : Fragment() {

    protected var subscriptions = CompositeSubscription()

    override fun onResume() {
        super.onResume()
        subscriptions = CompositeSubscription()
    }

    override fun onPause() {
        super.onPause()

        if(!subscriptions.isUnsubscribed){
            subscriptions.unsubscribe()
        }

        subscriptions.clear()
    }


}