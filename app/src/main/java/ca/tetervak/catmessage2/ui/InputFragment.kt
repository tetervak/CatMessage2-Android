package ca.tetervak.catmessage2.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ca.tetervak.catmessage2.R
import ca.tetervak.catmessage2.databinding.FragmentInputBinding
import ca.tetervak.catmessage2.model.Envelope

class InputFragment : Fragment() {

    interface Listener {
        fun showOutput(envelope: Envelope)
    }

    private var listener: Listener? = null

    private var _binding: FragmentInputBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentInputBinding.inflate(inflater, container, false)

        binding.sendButton.setOnClickListener { showOutput() }

        return binding.root
    }

    private fun showOutput(){
        // get urgent flag value
        val isUrgent: Boolean = binding.urgentCheckBox.isChecked
        // get the selected message text
        val textMessage = when (binding.messageGroup.checkedRadioButtonId) {
            R.id.purr_button -> getString(R.string.cat_purr)
            R.id.mew_button -> getString(R.string.cat_mew)
            R.id.hiss_button -> getString(R.string.cat_hiss)
            else -> getString(R.string.undefined)
        }
        listener?.showOutput(Envelope(isUrgent, textMessage))
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as Listener?
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}