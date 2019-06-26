package com.example.display_spec_and

interface MainContract {
    interface Presenter {
        fun onViewDestroy()
        fun onViewCreate()
    }

    interface View {
        fun showScreenSize()
    }
}