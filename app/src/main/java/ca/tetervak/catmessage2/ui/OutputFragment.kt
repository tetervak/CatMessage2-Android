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

    companion object {
        private const val ENVELOPE = "Envelope"

        @JvmStatic
        fun newInstance(envelope: Envelope?): OutputFragment {
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

        binding.backButton.setOnClickListener {
            parentFragmentManager.popBackStack(
                MainActivity.INPUT_TO_OUTPUT, FragmentManager.POP_BACK_STACK_INCLUSIVE
            )
        }

        showEnvelope()

        return binding.root
    }


    private fun showEnvelope() {

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}