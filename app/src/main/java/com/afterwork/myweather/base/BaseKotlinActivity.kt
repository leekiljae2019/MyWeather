package com.afterwork.myweather.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding


/**
 * BaseKotlinActivity<ActivitySbsMainBinding>와 같이 상속 받을 때 ActivitySbsMainBinding과
 * 같은 파일이 자동 생성되지 않는다면
 * 1. 해당 액티비티 레이아웃이 <layout></layout>로 감싸져 있는지 확인
 * 2. 다시 빌드 수행 or 클린 빌드 후 다시 빌드 수행
 * 3. 이름 확인 sbs_main_activity -> ActivitySbsMainBinding
 */

abstract class BaseKotlinActivity<T : ViewDataBinding, R : BaseKotlinViewModel> : AppCompatActivity() {

    lateinit var veiwDataBinding: T

    /**
     * setContentView를 호출할 layout 리소스 ID.
     * Ex). R.layout.activity_sbs_main
     */
    abstract val layoutResourceId: Int

    /**
     * ViewModel에 쓰일 변수
     */
    abstract val viewModel: R

    /**
     * layout을 띄운 직후 호출
     * 뷰나 액티비티의 속성등을 초기화
     * Ex). 리사이클러뷰, 툴바, 드로어뷰, ...
     */
    abstract fun initStartView()

    /**
     * 두번째로 호출
     * 데이터 바인딩 및 RxJava 설정
     * Ex). Rajava, Observe, DataBinding Observe, ...
     */
    abstract fun initDataBinding()


    /**
     * 바인딩 이후에 할일을 여기에 구현
     * 그 외에 설정할 것이 있으면 이곳에서 설정
     * Click Listener도 이곳에서 설
     */
    abstract fun initAfterBinding()

    private var isSetBackButtonValid = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        veiwDataBinding = DataBindingUtil.setContentView(this, layoutResourceId)

        initStartView()
        initDataBinding()
        initAfterBinding()
    }

}