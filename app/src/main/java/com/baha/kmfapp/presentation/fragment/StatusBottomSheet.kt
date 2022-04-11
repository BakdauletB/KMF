package com.baha.kmfapp.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.baha.kmfapp.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.dialog_show_info.view.*

class StatusBottomSheet : BottomSheetDialogFragment(){

    private val status = view?.findViewById<EditText>(R.id.status_field)
    private val ok = view?.findViewById<Button>(R.id.btn_ok)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return  inflater.inflate(R.layout.bottom_sheet_status,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ok?.setOnClickListener {
           if(status?.text!!.isEmpty()){
               Toast.makeText(
                   requireContext(),
                   "Недопустимый формат статуса",
                   Toast.LENGTH_LONG
               ).show()
           }
            Bundle().apply {
                putInt("Status",it.status.text.toString().toInt())
            }
            dismiss()
        }


    }


    companion object {
        val TAG = StatusBottomSheet::class.java.simpleName

//        @JvmStatic
//        fun newInstance() = RetailsSearchBottomSheet()
    }

}