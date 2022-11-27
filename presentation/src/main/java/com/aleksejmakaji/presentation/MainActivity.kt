package com.aleksejmakaji.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import com.aleksejmakaji.domain.error.DatabaseError
import com.aleksejmakaji.domain.error.FileFinderError
import com.aleksejmakaji.domain.error.NetworkError
import com.aleksejmakaji.presentation.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    fun showProgress(isVisible: Boolean) {
        binding.progressBarGlobal.isGone = !isVisible
    }

    /**
     * Handle generic errors
     */
    fun showError(error: FileFinderError) {
        when (error) {
            is DatabaseError -> {
                showSnackBar(error.throwable.message ?: this.getString(R.string.error_database))
            }
            is NetworkError -> {
                showSnackBar(error.throwable.message ?: this.getString(R.string.error_backend))
            }
            else -> {
                showSnackBar(this.getString(R.string.error_unknown))
            }
        }
    }

    fun showSnackBar(
        message: String,
        view: View? = null,
        length: Int? = null
    ) {
        Snackbar.make(view ?: binding.root, message, length ?: Snackbar.LENGTH_LONG).show()
    }

    fun showSnackBar(
        stringId: Int,
        view: View? = null,
        length: Int? = null
    ) {
        showSnackBar(getString(stringId), view, length)
    }
}
