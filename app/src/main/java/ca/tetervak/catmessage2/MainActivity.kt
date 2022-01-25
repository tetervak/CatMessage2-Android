package ca.tetervak.catmessage2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ca.tetervak.catmessage2.ui.InputFragment
import ca.tetervak.catmessage2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    companion object {
        const val INPUT_TO_OUTPUT = "Input_to_Output"
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, InputFragment())
                .commit()
        }
    }

}