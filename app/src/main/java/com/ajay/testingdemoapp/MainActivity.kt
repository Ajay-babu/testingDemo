package com.ajay.testingdemoapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ajay.testingdemoapp.databinding.ActivityMainBinding
import com.ajay.testingdemoapp.repository.UserDatabaseRepo
import com.ajay.testingdemoapp.roomDB.AppDatabase
import com.ajay.testingdemoapp.viewModelFactory.MainActivityViewModelFactory
import com.ajay.testingdemoapp.views.activity.UpdateProfileDialogActivity
import com.ajay.testingdemoapp.views.viewmodel.MainActivityViewModel
import com.google.android.material.snackbar.Snackbar
import com.shivansh.officetask.utils.ImageConverter

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration : AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel : MainActivityViewModel
    //https://github.com/Ajay-babu/testingDemo.git
   // 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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = DataBindingUtil.setContentView(this,R.layout.activity_main)


        //Taking email From intent
        val email : String? = intent.getStringExtra("userEmail")

        //Creating Dao and Repository for ViewModelFactory
        val userDao = AppDatabase.getInstance(applicationContext).userDao()
        val repository = UserDatabaseRepo(userDao)

        //Initializing ViewModel with ViewModel Factory
        viewModel = ViewModelProvider(this,
            MainActivityViewModelFactory(repository,email)
        )[MainActivityViewModel::class.java]

        setSupportActionBar(binding.appBarMain.toolbar)

        binding.appBarMain.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }



        val view : NavigationView = findViewById(R.id.nav_view)
        val headerView : View = view.getHeaderView(0)

        val navUsername = headerView.findViewById<View>(R.id.userName) as TextView
        val navEmail = headerView.findViewById<View>(R.id.userEmail) as TextView
        val navPhoto = headerView.findViewById<View>(R.id.userImage) as ImageView
        val navEditButton = headerView.findViewById<View>(R.id.EditProfile) as ImageButton


        viewModel.getUser().observe(this, Observer { user ->
            "Name: ${user.name}".also { navUsername.text = it }
            "Email: ${user.email}".also { navEmail.text = it }
            navPhoto.setImageBitmap(ImageConverter.convertStringTOBitmap(user.image))


            navEditButton.setOnClickListener{
                val intent = Intent(this, UpdateProfileDialogActivity::class.java).apply {
                    putExtra("emailFromHome",user.email)
                    putExtra("passwordFromHome",user.password)
                }
                startActivity(intent)
            }

        })


        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow, R.id.nav_logout
            ), drawerLayout
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_logout ->{
                Toast.makeText(this, "it has clicked", Toast.LENGTH_SHORT).show()
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}