package com.anatoliyvinokurov.easyrubik

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.android.play.core.review.ReviewManager
import com.google.android.play.core.review.ReviewManagerFactory
import com.google.android.play.core.review.ReviewInfo
import com.google.android.play.core.review.ReviewException


/**
 * Fragment class representing Step 8 of the process.
 */
class Step8Fragment : Fragment() {
    private lateinit var manager: ReviewManager

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
        manager = ReviewManagerFactory.create(requireContext())
        showARHD()
        return inflater.inflate(
            R.layout.layout_step8, container, false
        )
    }

    /**
     * Method to show the advertisement.
     * Gets a reference to the MainActivity instance and calls the showInterstitial() function.
     */
    private fun showARHD() {
        val request: Task<ReviewInfo> = manager.requestReviewFlow()
        request.addOnCompleteListener(OnCompleteListener { task ->
            if (task.isSuccessful) {
                // We got the ReviewInfo object
                val reviewInfo: ReviewInfo = task.result
                // Use the reviewInfo object to show the review dialog
            } else {
                // There was some problem, log or handle the error code.
                val exception: Exception? = task.exception
                if (exception is ReviewException) {
                    // Handle the reviewErrorCode
                    println("Heloo oooooooooooooooooooooooooooooo")
                }
            }
        })
    }
}
