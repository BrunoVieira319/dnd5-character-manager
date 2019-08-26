package com.fundaec.ddcharactermanager.fragments

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.Volley

import com.fundaec.ddcharactermanager.R
import com.fundaec.ddcharactermanager.models.CharacterInfo
import com.fundaec.ddcharactermanager.network.GsonRequest
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.character_layout_item.view.*
import kotlinx.android.synthetic.main.fragment_character_main_info.*

private const val CHARACTER_ID = "characterId"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [MainInfoCharacterFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [MainInfoCharacterFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class CharacterMainInfoFragment : Fragment() {

    private var characterId: String? = null
    private var queue: RequestQueue? = null
//    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            characterId = it.getString(CHARACTER_ID)
        }

        queue = Volley.newRequestQueue(activity?.baseContext)
        fetchCharacter()
    }

    private fun fetchCharacter() {
        val request = GsonRequest(
            "http://192.168.50.65:8080/v1/characters/$characterId",
            CharacterInfo::class.java,
            Response.Listener {
                characterNameInfo.text = it.characterName
                Picasso.get()
                    .load(it.image)
                    .fit()
                    .centerCrop()
                    .into(characterImageInfo)
            },
            Response.ErrorListener {
                Toast.makeText(activity?.baseContext, it.message, Toast.LENGTH_SHORT).show()
            }
        )
        queue?.add(request)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_character_main_info, container, false)
    }
//
//    fun onButtonPressed(uri: Uri) {
//        listener?.onFragmentInteraction(uri)
//    }
//
//    override fun onDetach() {
//        super.onDetach()
//        listener = null
//    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
//    interface OnFragmentInteractionListener {
//        fun onFragmentInteraction(uri: Uri)
//    }

    companion object {
        @JvmStatic
        fun newInstance(characterId: String) =
            CharacterMainInfoFragment().apply {
                arguments = Bundle().apply {
                    putString(CHARACTER_ID, characterId)
                }
            }
    }
}
