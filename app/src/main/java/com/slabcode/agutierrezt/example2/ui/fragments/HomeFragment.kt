package com.slabcode.agutierrezt.example2.ui.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.preference.PreferenceManager
import com.bumptech.glide.Glide
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.ktx.awaitMap
import com.google.maps.android.ktx.awaitMapLoad
import com.slabcode.agutierrezt.example2.R
import com.slabcode.agutierrezt.example2.data.models.StoreInfo
import com.slabcode.agutierrezt.example2.databinding.FragmentHomeBinding
import com.slabcode.agutierrezt.example2.ui.viewmodels.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.osmdroid.config.Configuration.*
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.Marker


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_home, container, false)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        getInstance().load(requireContext(), PreferenceManager.getDefaultSharedPreferences(requireContext()))
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        homeViewModel.loadStoreInfo()
        homeViewModel.info.observe(viewLifecycleOwner, Observer { store ->
            binding.homeTitle.text = store.name
            binding.homeAddress.text = store.address
            binding.homeDescription.text = store.description
            Glide.with(binding.root).load(store.image).into(binding.homeImage)

            if(ContextCompat.checkSelfPermission(requireContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(requireContext(),
                    Manifest.permission.ACCESS_NETWORK_STATE) == PackageManager.PERMISSION_GRANTED) {
                binding.homeOsmMap.visibility = View.INVISIBLE
                binding.homeOsmMap.setTileSource(TileSourceFactory.MAPNIK)
                val mapController = binding.homeOsmMap.controller
                mapController.setZoom(18.0)
                val startPoint = GeoPoint(store.lat!!, store.lng!!)
                mapController.setCenter(startPoint)

                val marker = Marker(binding.homeOsmMap)
                marker.position = startPoint
                marker.setAnchor(
                    Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM
                )
                marker.title = store.name
                binding.homeOsmMap.overlays.add(marker)
            }

            val mapFragment = childFragmentManager.findFragmentById(R.id.home_map) as SupportMapFragment
            lifecycleScope.launchWhenStarted {
                val googleMap = mapFragment.awaitMap()
                addMarker(googleMap, store)
                googleMap.awaitMapLoad()

                val bounds = LatLngBounds.builder()
                    .include(LatLng(store.lat!!, store.lng!!))
                    .build()
                googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 20))

            }

        })


    }

    private fun addMarker(map: GoogleMap, store: StoreInfo) {
        map.addMarker(
            MarkerOptions()
                .position(LatLng(store.lat!!, store.lng!!))
                .title(store.name)
        )
    }

}