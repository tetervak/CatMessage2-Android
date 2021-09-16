package ca.tetervak.catmessage2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import ca.tetervak.catmessage2.model.Envelope
import ca.tetervak.catmessage2.ui.InputFragment
import ca.tetervak.catmessage2.ui.OutputFragment
import ca.tetervak.catmessage2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), InputFragment.Listener, OutputFragment.Listener {
    companion object{
        const val INPUT_TO_OUTPUT = "Input_to_Output"
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, InputFragment())
                .commit()
        }
    }

    override fun showOutput(envelope: Envelope) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, OutputFragment.newInstance(envelope))
            .addToBackStack(INPUT_TO_OUTPUT)
            .commit()
    }

    override fun showInput(){
        supportFragmentManager.popBackStack(
            INPUT_TO_OUTPUT, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
}