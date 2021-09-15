package ca.tetervak.catmessage2.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ca.tetervak.catmessage2.R
import ca.tetervak.catmessage2.databinding.FragmentInputBinding
import ca.tetervak.catmessage2.databinding.FragmentOutputBinding
import ca.tetervak.catmessage2.model.Envelope

class OutputFragment : Fragment() {

    interface Listener{
        fun showInput()
    }
    private var listener: Listener? = null

    companion object{
        private const val ENVELOPE = "Envelope"

        @JvmStatic
        fun newInstance(envelope: Envelope?): OutputFragment{
            val fragment = OutputFragment()
            val arguments = Bundle()
            arguments.putSerializable(ENVELOPE, envelope)
            fragment.arguments = arguments
            return fragment
        }
    }

    private var _binding: FragmentOutputBinding? = null
    private val binding get() = _binding!!

    private var envelope: Envelope? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            envelope = it.getSerializable(ENVELOPE) as Envelope?
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentOutputBinding.inflate(inflater, container, false)

        binding.backButton.setOnClickListener { listener?.showInput() }

        showEnvelope()

        return binding.root
    }


    private fun showEnvelope(){

        envelope?.apply {
            binding.isUrgentOutput.text =
                if (isUrgent) {
                    getString(R.string.urgent)
                } else {
                    getString(R.string.not_urgent)
                }
            binding.messageText.text = textMessage
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as Listener
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

}