package com.blackpuppydev.testcodemobile.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import com.blackpuppydev.testcodemobile.adapter.ProductAdapter
import com.blackpuppydev.testcodemobile.api.repository.ProductRepository
import com.blackpuppydev.testcodemobile.api.response.Product
import com.blackpuppydev.testcodemobile.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private var binding:ActivityHomeBinding? = null
    private var allProduct:ArrayList<Product>? = null
    private var filterDropDownProduct: ArrayList<Product>? = arrayListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        val dropdownAdapter = ArrayAdapter(this@HomeActivity,android.R.layout.simple_spinner_dropdown_item,getListDropdown())
        ProductRepository.newInstance().getProductItem { products ->
            allProduct = products
            binding!!.listItem.apply {
                layoutManager = GridLayoutManager(context,1)
                adapter = object : ProductAdapter(allProduct!!,false,false){
                    override fun onSelectProduct(id: String) {
                        startActivity(Intent(this@HomeActivity,DetailActivity::class.java).putExtra("id",id))
                    }
                }
            }

            binding!!.dropdown.apply {
                adapter = dropdownAdapter
                onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                        filterWithDropDown(p2)
                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) {}
                }
            }
        }

        binding!!.search.clearFocus()
        binding!!.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(text: String?): Boolean { return false }

            override fun onQueryTextChange(text: String): Boolean {
                filterSearch(text)
                return false
            }

        })
    }

    private fun getListDropdown():Array<String>{
        return arrayOf("All","> 1000$ & %>0","Total price/piece","Rating","Total price")
    }

    private fun filterSearch(text:String) {

        val products:ArrayList<Product> = arrayListOf()

        for (item in filterDropDownProduct!!){
            if (item.title.lowercase().contains(text.lowercase())){
                products.add(item)
            }
        }

        binding!!.listItem.apply {
            layoutManager = GridLayoutManager(context,1)
            adapter = object : ProductAdapter(products,false,false){
                override fun onSelectProduct(id: String) {
                    startActivity(Intent(this@HomeActivity,DetailActivity::class.java).putExtra("id",id))
                }
            }
        }
    }

    private fun filterWithDropDown(id:Int){

        Log.e("filterWithDropDown : ",id.toString())

        var showPricePiece = false
        var showTotalPrice = false
        filterDropDownProduct = arrayListOf()

        when(id){
            0 -> filterDropDownProduct = allProduct
            1 -> {
                for (product in allProduct!!){
                    if (product.price.inc() >= 1000F){
                        filterDropDownProduct!!.add(product)
                    }
                }
            }
            2 -> { //Price per Piece
                filterDropDownProduct = allProduct
                showPricePiece = true
            }
            3 -> { //Rating desc and price asc
                filterDropDownProduct = ArrayList(allProduct?.sortedByDescending{it.rating}!!.sortedBy{it.price})
                Log.e("filterDropDownProduct rating dsc : " , filterDropDownProduct.toString())
            }
            4 -> { //Total price
                filterDropDownProduct = allProduct
                showTotalPrice = true
            }
        }


        binding!!.listItem.apply {
            layoutManager = GridLayoutManager(context,1)
            adapter = object : ProductAdapter(filterDropDownProduct!!,showPricePiece,showTotalPrice){
                override fun onSelectProduct(id: String) {
                    startActivity(Intent(this@HomeActivity,DetailActivity::class.java).putExtra("id",id))
                }
            }
        }

    }




}