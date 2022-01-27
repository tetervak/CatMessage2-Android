package ca.tetervak.catmessage2.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import ca.tetervak.catmessage2.MainActivity
import ca.tetervak.catmessage2.R
import ca.tetervak.catmessage2.databinding.FragmentOutputBinding
import ca.tetervak.catmessage2.model.Envelope

class OutputFragment : Fragment() {

    companion object{
        const val ENVELOPE = "Envelope"
    }

    private var _binding: FragmentOutputBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentOutputBinding.inflate(inflater, container, false)

        val envelope = arguments?.getSerializable(ENVELOPE) as Envelope?
        if(envelope is Envelope) {
            showEnvelope(envelope)
        }

        binding.backButton.setOnClickListener {
            showInput()
        }

        return binding.root
    }


    private fun showEnvelope(envelope: Envelope){
        binding.isUrgentOutput.text =
            if (envelope.isUrgent) {
                getString(R.string.urgent)
            } else {
                getString(R.string.not_urgent)
            }
        binding.messageText.text = envelope.textMessage
    }

    private fun showInput(){
        parentFragmentManager.popBackStack(
            MainActivity.INPUT_TO_OUTPUT, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}