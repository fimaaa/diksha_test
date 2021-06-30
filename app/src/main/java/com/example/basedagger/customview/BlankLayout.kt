package com.example.basedagger.customview

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.example.basedagger.R
import com.example.basedagger.databinding.LayoutBlankBinding
import com.example.basedagger.utill.visible
import org.jetbrains.anko.sdk27.coroutines.onClick
import java.net.HttpURLConnection
import javax.net.ssl.HttpsURLConnection

@SuppressLint("CustomViewStyleable")
class BlankLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(
    context,
    attrs,
    defStyleAttr
) {
    val binding: LayoutBlankBinding

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_blank, this, true)
        binding = LayoutBlankBinding.bind(view)
        attrs?.let {
            val styledAttributes = context.obtainStyledAttributes(it, R.styleable.LayoutBlank, 0, 0)
            val img = styledAttributes.getResourceId(
                R.styleable.LayoutBlank_img,
                0
            )
            val text = styledAttributes.getString(
                R.styleable.LayoutBlank_text
            )
            val type = styledAttributes.getInt(
                R.styleable.LayoutBlank_blankType,
                0
            )
            setType(type)
            setImg(img)
            setText(text)
            styledAttributes.recycle()
        }
    }

    fun setType(
        errorCode: Int,
        message: String? = context.getString(R.string.error_default),
        onClick: (() -> Unit)? = null
    ) {
        when (errorCode) {
            HttpURLConnection.HTTP_NOT_FOUND, HttpURLConnection.HTTP_NO_CONTENT -> {
                setImg(R.drawable.ic_data_not_found)
                binding.txtBlank.text = context.getString(R.string.error_search_notfound)
                setButtonVisibility(false)
            }
            HttpURLConnection.HTTP_UNAUTHORIZED -> {
                setImg(R.drawable.ic_error_button)
                binding.txtBlank.text = context.getString(R.string.error_unauthorized)
                binding.btnBlank.text = context.getString(R.string.login)
                binding.btnBlank.onClick {
//                    it?.getParentActivity()?.login()
                }
                setButtonVisibility(true)
            }
            HttpsURLConnection.HTTP_BAD_REQUEST, HttpsURLConnection.HTTP_FORBIDDEN -> {
                setImg(R.drawable.ic_error_nobutton)
                binding.txtBlank.text = message
                setButtonVisibility(false)
            }
            else -> {
                setImg(R.drawable.ic_error_nobutton)
                binding.txtBlank.text = message
                binding.btnBlank.text = context.getString(R.string.retry)
                binding.btnBlank.onClick {
                    onClick?.invoke()
                }
                setButtonVisibility(true)
            }
        }
    }

    fun setImg(value: Int) {
        if (value != 0) {
            binding.imgBlank.setImageResource(value)
        }
    }

    private fun setText(value: String?) {
        if (value != null) {
            binding.txtBlank.text = value
        }
    }

    fun setOnClick(text: String, onClick: () -> Unit) {
        binding.btnBlank.visible()
        binding.btnBlank.text = text
        binding.btnBlank.onClick {
            onClick.invoke()
        }
    }

    fun setButtonVisibility(isVisible: Boolean) {
        binding.btnBlank.isVisible = isVisible
    }
}