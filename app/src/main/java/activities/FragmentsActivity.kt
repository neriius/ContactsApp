package activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.contactsapp.databinding.ActivityFragmentsBinding

class FragmentsActivity : AppCompatActivity() {

    private lateinit var binding:ActivityFragmentsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFragmentsBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}