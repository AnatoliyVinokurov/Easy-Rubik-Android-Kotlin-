package com.anatoliyvinokurov.easyrubik

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.android.play.core.review.ReviewInfo
import com.google.android.play.core.review.ReviewManager
import com.google.android.play.core.review.ReviewManagerFactory
import com.google.android.play.core.review.ReviewException

/**
 * Fragment class representing Step 7 of the process.
 */
class Step7Fragment : Fragment() {
    private lateinit var manager: ReviewManager
    private var buttonAddCommit: Button? = null

    /**
     * Called to create the view hierarchy associated with the fragment.
     *
     * @param inflater           The LayoutInflater object that can be used to inflate any views
     * @param container          The parent view that the fragment's UI should be attached to
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous saved state
     * @return The View for the fragment's UI, or null
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.layout_step7, container, false)
        showAdRekl()
        buttonAddCommit = view.findViewById(R.id.addCommit)
        buttonAddCommit?.setOnClickListener {
            showARHD()
        }

        return view
    }

    /**
     * Method to show the advertisement.
     * Gets a reference to the MainActivity instance and calls the showInterstitial() function.
     */
    fun showAdRekl() {
        // Get a reference to the MainActivity instance
        val mainActivity = activity as MainActivity

        // Call the showInterstitial() function of MainActivity
        mainActivity.showInterstitial()
    }

    private fun showARHD() {
        manager = ReviewManagerFactory.create(requireContext())
        val request = manager.requestReviewFlow()
        request.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // We got the ReviewInfo object
                val reviewInfo = task.result
                reviewInfo?.let { info ->
                    val flow = manager.launchReviewFlow(requireActivity(), info)
                    flow.addOnCompleteListener { reviewFlowTask ->
                        // The review flow has finished
                        if (reviewFlowTask.isSuccessful) {
                            // Review completed successfully
                            val reviewFlowResult = reviewFlowTask.result
                            // TODO: Handle successful review completion
                        } else {
                            // Review failed or was canceled
                            val exception = reviewFlowTask.exception
                            if (exception is ReviewException) {
                                // There was an error in the API response or the API is not available
                                val reviewException = exception
                                // TODO: Handle the error if needed
                            } else {
                                // There was another type of exception
                                // TODO: Handle the exception if needed
                            }
                        }
                    }
                }
            } else {
                // There was some error
                val exception = task.exception
                if (exception is ReviewException) {
                    // There was an error in the API response or the API is not available
                    val reviewException = exception
                    // TODO: Handle the error if needed
                } else {
                    // There was another type of exception
                    // TODO: Handle the exception if needed
                }
            }
        }
    }
}
