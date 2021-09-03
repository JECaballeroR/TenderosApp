package com.slabcode.agutierrezt.example2.ui.activities

import android.animation.Animator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.slabcode.agutierrezt.example2.data.models.Comment
import com.slabcode.agutierrezt.example2.data.models.Product
import com.slabcode.agutierrezt.example2.data.models.StoreInfo
import com.slabcode.agutierrezt.example2.databinding.ActivitySplashScreenBinding
import com.slabcode.agutierrezt.example2.ui.viewmodels.SplashViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    private val splashViewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashScreenBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        splashViewModel.loggedIn()
        splashViewModel.insert(
            listOf(
                Comment(1,"https://toppng.com/uploads/preview/blue-person-icon-blue-person-icon-115629039821nthr4gtiu.png", "Pepito Perez", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s"),
                Comment(2,"https://toppng.com/uploads/preview/blue-person-icon-blue-person-icon-115629039821nthr4gtiu.png", "Juanito Perez", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s"),
                Comment(3,"https://toppng.com/uploads/preview/blue-person-icon-blue-person-icon-115629039821nthr4gtiu.png", "Andres Gutierrez", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s"),
                Comment(4,"https://toppng.com/uploads/preview/blue-person-icon-blue-person-icon-115629039821nthr4gtiu.png", "Camila Becerra", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s"),
                Comment(5,"https://toppng.com/uploads/preview/blue-person-icon-blue-person-icon-115629039821nthr4gtiu.png", "Ivan Gonzales", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s"),
                Comment(6,"https://toppng.com/uploads/preview/blue-person-icon-blue-person-icon-115629039821nthr4gtiu.png", "Pepito Perez", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s"),
            ),
            listOf(
                Product(1,"https://www.latinfoodus.com/wp-content/uploads/2017/11/chocoRamo_mini.jpg","Producto 1", "$5.000", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s"),
                Product(2,"https://pngimg.com/uploads/burger_sandwich/burger_sandwich_PNG96777.png","Producto 2", "$10.000","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s"),
                Product(3,"https://www.latinfoodus.com/wp-content/uploads/2017/11/chocoRamo_mini.jpg","Producto 3", "$5.000","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s"),
                Product(4,"https://pngimg.com/uploads/burger_sandwich/burger_sandwich_PNG96777.png","Producto 4", "$10.000","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s"),
            ),
            StoreInfo(
                1,
                "Desertores 42",
                "https://w7.pngwing.com/pngs/80/585/png-transparent-post-board-gaming-logo-brand-hobby-shop-organization-post-card-text-logo-video-game.png",
                "Calle falsa 123",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."
            )
        )


        binding.splashAnimation.imageAssetsFolder = "images"
        binding.splashAnimation.playAnimation()


        binding.splashAnimation.addAnimatorListener(object: Animator.AnimatorListener {
            override fun onAnimationStart(p0: Animator?) {
            }

            override fun onAnimationEnd(p0: Animator?) {
                splashViewModel.user.observe(this@SplashScreenActivity, Observer { user ->
                    if(user != null) {
                        val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        val intent = Intent(this@SplashScreenActivity, LoginActivity::class.java)
                        startActivity(intent)
                    }
                    finish()

                })

            }

            override fun onAnimationCancel(p0: Animator?) {
            }

            override fun onAnimationRepeat(p0: Animator?) {
            }

        })
    }
}