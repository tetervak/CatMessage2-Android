package ca.tetervak.catmessage2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.add
import androidx.fragment.app.commit
import ca.tetervak.catmessage2.model.Envelope
import ca.tetervak.catmessage2.ui.InputFragment
import ca.tetervak.catmessage2.ui.OutputFragment
import ca.tetervak.catmessage2.databinding.ActivityMainBinding
import ca.tetervak.catmessage2.ui.OutputFragment.Companion.ENVELOPE

class MainActivity : AppCompatActivity() {
    companion object{
        const val INPUT_TO_OUTPUT = "Input_to_Output"
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(savedInstanceState == null){
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<InputFragment>(R.id.fragment_container)
            }
        }
    }

}