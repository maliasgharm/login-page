package org.noandish.library.loginpage

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.Typeface
import android.support.v4.widget.TextViewCompat
import android.text.InputFilter
import android.text.InputType
import android.text.SpannableString
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.*


/**
 * Created by AliasgharMirzazade on 8/25/18.
 */
class Login @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {


    private val btnSkip = TextView(context)
    private var customTextInputUsername = ""
    private val edtPassword2 = EditText(context)
    private var idNumber = 0
    private var layerLogin: RelativeLayout
    private var layerMainSplash: RelativeLayout
    private var layerRegister: RelativeLayout
    private val layoutMore = LinearLayout(context)
    var onLoginChangeListener: OnLoginChangeListener? = null
    private val screenHeight: Int
        get() = Resources.getSystem().displayMetrics.heightPixels
    private val screenWidth: Int
        get() = Resources.getSystem().displayMetrics.widthPixels
    private val svLogin = ScrollView(context)
    private val svRegister = ScrollView(context)
    private var typeLoginView = 3
    private var typeUsername = 5
    override fun addView(child: View?, params: ViewGroup.LayoutParams?) {
        when {
            child!!.tag == Login::class.java -> {
                super.addView(child, params)
            }
            child is EditText -> {
                if (child.id == -1) {
                    child.id = idNumber
                    idNumber++
                }
                addCustomEditText(child.hint.toString(), child.id)
            }
            else -> {

                val params2 = LinearLayout.LayoutParams((screenWidth / 1.5).toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)
                params2.setMargins(0, 15, 0, 15)
                params2.gravity = Gravity.CENTER
                layoutMore.addView(child, params2)

            }
        }
    }

    @Suppress("MemberVisibilityCanBePrivate")
    fun addCustomEditText(hint: String, id: Int) {
        val params2 = LinearLayout.LayoutParams((screenWidth / 1.5).toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)
        params2.setMargins(0, 15, 0, 15)
        val edtCustom = EditText(context)
        edtCustom.setPadding(9, 9, 9, 9)
        if (typeUsername == TYPE_INPUT_USERNAME_MOBILE)
            edtCustom.maxEms = 11

        edtCustom.hint = hint
        edtCustom.id = id
        edtCustom.gravity = Gravity.CENTER
        edtCustom.textSize = 18f
        edtCustom.setBackgroundResource(R.drawable.bg_edt_login)
        edtCustom.inputType = EditorInfo.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
        layoutMore.addView(edtCustom, params2)


        for (i in 0 until layoutMore.childCount) {

            val view = layoutMore.getChildAt(i)

            if (i < layoutMore.childCount - 1) {
                val view2 = layoutMore.getChildAt(i + 1)
                view.nextFocusDownId = view2.id
                view.nextFocusForwardId = view2.id
                view.nextFocusRightId = view2.id
            } else {
                view.nextFocusDownId = R.id.btn_register
                view.nextFocusForwardId = R.id.btn_register
                view.nextFocusRightId = R.id.btn_register
            }

        }

        if (layoutMore.childCount <= 0) {
            edtPassword2.nextFocusDownId = R.id.btn_register
            edtPassword2.nextFocusForwardId = R.id.btn_register
            edtPassword2.nextFocusRightId = R.id.btn_register
        } else {
            val view = layoutMore.getChildAt(0)
            edtPassword2.nextFocusDownId = view.id
            edtPassword2.nextFocusForwardId = view.id
            edtPassword2.nextFocusRightId = view.id
        }
    }

    /**
     *  [inputUsername] a [type]  for type input field username and access to
     *  enter a special character
     */
    fun inputUsername(type: Int) {
        this.typeUsername = type
    }

    /**
     * [showLogin] a function for show page login
     */
    @Suppress("MemberVisibilityCanBePrivate")
    fun showLogin() {
        layerMainSplash.animate().x(screenWidth.toFloat()).start()
        svRegister.animate().x(screenWidth.toFloat()).start()
        svLogin.animate().x(0f).start()
    }

    /**
     * [showRegister] a function for show page register
     * if [typeLoginView] equals [TYPE_WITH_REGISTER_AND_SKIP] OR [TYPE_WITH_REGISTER]
     */
    @Suppress("MemberVisibilityCanBePrivate")
    fun showRegister() {
        if (typeLoginView == TYPE_WITH_REGISTER_AND_SKIP || typeLoginView == TYPE_WITH_REGISTER) {
            svLogin.animate().x(screenWidth.toFloat()).start()
            layerMainSplash.animate().x(screenWidth.toFloat()).start()
            svRegister.animate().x(0f).start()
        }
    }

    private fun isInteger(str: String?): Boolean {
        if (str == null) {
            return false
        }
        if (str.isEmpty()) {
            return false
        }
        var i = 0
        if (str[0] == '-') {
            if (str.length == 1) {
                return false
            }
            i = 1
        }
        while (i < str.length) {
            val c = str[i]
            if (c < '0' || c > '9') {
                return false
            }
            i++
        }
        return true
    }

    private fun login() {

        val linearLayout = LinearLayout(context)
        linearLayout.orientation = LinearLayout.VERTICAL
        linearLayout.gravity = Gravity.CENTER
        layerLogin.addView(linearLayout, ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))


        val params1 = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, screenHeight / 6)
        val tvTitle = TextView(context)
        params1.bottomMargin = 40
        tvTitle.text = context.getString(R.string.login)
        tvTitle.textSize = 22f
        tvTitle.gravity = Gravity.CENTER
        tvTitle.setTypeface(null, Typeface.BOLD)
        tvTitle.setTextColor(Color.parseColor("#2D8469"))

        linearLayout.addView(tvTitle, params1)

        val params2 = LinearLayout.LayoutParams((screenWidth / 1.5).toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)
        params2.setMargins(0, 15, 0, 15)
        val edtUser = EditText(context)
        edtUser.setPadding(9, 9, 9, 9)
        if (typeUsername == TYPE_INPUT_USERNAME_MOBILE) {
            edtUser.filters += InputFilter.LengthFilter(11)
            edtUser.inputType = InputType.TYPE_CLASS_NUMBER
        }
        edtUser.hint = context.getString(R.string.email_or_mobile)
        edtUser.gravity = Gravity.CENTER
        edtUser.textSize = 18f
        edtUser.setBackgroundResource(R.drawable.bg_edt_login)
        edtUser.inputType = EditorInfo.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
        linearLayout.addView(edtUser, params2)
        edtUser.nextFocusDownId = R.id.edt_pass_login
        edtUser.nextFocusForwardId = R.id.edt_pass_login
        edtUser.nextFocusRightId = R.id.edt_pass_login

        val edtPassword = EditText(context)
        edtPassword.gravity = Gravity.CENTER
        edtPassword.id = R.id.edt_pass_login
        edtPassword.setPadding(7, 7, 7, 7)
        edtPassword.hint = context.getString(R.string.password)
        edtPassword.inputType = EditorInfo.TYPE_TEXT_VARIATION_PASSWORD

        edtPassword.nextFocusDownId = R.id.btn_login
        edtPassword.nextFocusForwardId = R.id.btn_login
        edtPassword.nextFocusRightId = R.id.btn_login

        val textUser: String = if (typeUsername == TYPE_INPUT_USERNAME_MOBILE_EMAIL_USERNAME
        ) {
            context.getString(R.string.username_or_mobile_or_email)
        } else if (typeUsername == TYPE_INPUT_USERNAME_MOBILE_EMAIL) {
            context.getString(R.string.email_or_mobile)
        } else if (typeUsername == TYPE_INPUT_USERNAME_MOBILE
                || typeUsername == TYPE_INPUT_USERNAME_USERNAME_MOBILE) {
            edtUser.filters += InputFilter.LengthFilter(11)
            edtUser.inputType = InputType.TYPE_CLASS_NUMBER
            context.getString(R.string.mobile)
        } else if (typeUsername == TYPE_INPUT_USERNAME_USERNAME
                || typeUsername == TYPE_INPUT_USERNAME_EMAIL
                || typeUsername == TYPE_INPUT_USERNAME_USERNAME_EMAIL) {
            context.getString(R.string.email)
        } else {
            customTextInputUsername
        }
        edtUser.hint = textUser


        edtPassword.setBackgroundResource(R.drawable.bg_edt_login)
        linearLayout.addView(edtPassword, params2)

        edtPassword.inputType = InputType.TYPE_CLASS_TEXT or
                InputType.TYPE_TEXT_VARIATION_PASSWORD

        val params3 = LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
        val params4 = LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
        //  params3.rightMargin = 50
        params3.topMargin = 25
        params3.addRule(ALIGN_PARENT_LEFT)
        params4.addRule(ALIGN_BOTTOM, R.id.btn_login)
        params4.addRule(ALIGN_PARENT_TOP)
        params4.topMargin = 100
        params4.addRule(ALIGN_PARENT_RIGHT)
        val btnLogin = Button(context)
        btnLogin.setBackgroundResource(R.drawable.bg_btn_login)
        btnLogin.text = context.getString(R.string.login)
        btnLogin.id = R.id.btn_login
        btnLogin.setTextColor(Color.WHITE)
        linearLayout.addView(btnLogin, params3)
        btnLogin.setOnClickListener { login(edtUser, edtPassword) }
        if (typeLoginView == TYPE_WITH_REGISTER
                || typeLoginView == TYPE_WITH_REGISTER_AND_SKIP) {
            val btnRegister = TextView(context)
            val spanText = SpannableString(context.getString(R.string.register)
            )
            btnRegister.text = spanText
            TextViewCompat.setTextAppearance(btnRegister,
                    R.style.AudioFileInfoOverlayText)
            btnRegister.gravity = Gravity.CENTER
            linearLayout.addView(btnRegister, params4)
            btnRegister.setOnClickListener { showRegister() }
        }
        btnSkip.visibility = if (typeLoginView == TYPE_WITH_SKIP || typeLoginView == TYPE_WITH_REGISTER_AND_SKIP)
            View.VISIBLE else View.INVISIBLE


    }

    private fun login(edt_username: EditText, edt_password: EditText) {
        val username = edt_username.text.toString()
        val password = edt_password.text.toString()
        if (username == "") {
            edt_username.error = context.getString(R.string.this_section_can_not_be_empty)
            return
        }
        val isMobile = isInteger(username)
        val isEmail = !isMobile && username.contains("@")

        if ((typeUsername == TYPE_INPUT_USERNAME_MOBILE_EMAIL_USERNAME
                        || typeUsername == TYPE_INPUT_USERNAME_MOBILE
                        || typeUsername == TYPE_INPUT_USERNAME_MOBILE_EMAIL)
                && isMobile
        ) {

            if (username.isNotEmpty() && username[0] != '0') {
                edt_username.error = context.getString(R.string.mobile_must_by_zero)
                return
            }
            if (username.length < 11) {
                edt_username.error = context.getString(R.string.mobile_is_short)
                return
            }
            if (username.length > 11) {
                edt_username.error = context.getString(R.string.mobile_invalid)
                return
            }

        } else if ((typeUsername == TYPE_INPUT_USERNAME_MOBILE_EMAIL_USERNAME
                        || typeUsername == TYPE_INPUT_USERNAME_EMAIL
                        || typeUsername == TYPE_INPUT_USERNAME_MOBILE_EMAIL) && isEmail) {
            if (username.length < 7) {
                edt_username.error = context.getString(R.string.email_is_short)
                return
            }
        } else if (!isMobile && !isEmail) {
            edt_username.error = context.getString(R.string.email_invalid)
            return
        } else {
            edt_username.error = context.getString(R.string.username_invalid)
            return
        }
        if (password == "") {
            edt_password.error = context.getString(R.string.this_section_can_not_be_empty)
            return
        }
        if (password.length < 8) {
            edt_password.error = context.getString(R.string.password_is_short)
            return
        }
        if (password.length > 32) {
            edt_password.error = context.getString(R.string.password_is_long)
            return
        }
        if (onLoginChangeListener != null)
            onLoginChangeListener!!.onLogged(username, password)
    }

    private fun main() {
        val linearLayout = LinearLayout(context)
        linearLayout.orientation = LinearLayout.VERTICAL
        linearLayout.gravity = Gravity.CENTER
        val paramsBtn = LayoutParams((screenWidth / 1.2).toInt(), LayoutParams.WRAP_CONTENT)
        paramsBtn.topMargin = 50
        paramsBtn.bottomMargin = 50
        val btnLogin = Button(context)
        btnLogin.text = context.getString(R.string.login)
        btnLogin.setBackgroundResource(R.drawable.bg_btn_main_login)
        btnLogin.setPadding(30, 0, 30, 0)
        linearLayout.addView(btnLogin, paramsBtn)
        btnLogin.setOnClickListener { showLogin() }
        if (typeLoginView == TYPE_WITH_REGISTER
                || typeLoginView == TYPE_WITH_REGISTER_AND_SKIP) {
            val btnRegister = Button(context)
            btnRegister.text = context.getString(R.string.register)
            btnRegister.setPadding(30, 0, 30, 0)
            btnRegister.setBackgroundResource(R.drawable.bg_btn_main_login)
            linearLayout.addView(btnRegister, paramsBtn)
            val params = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
            params.addRule(CENTER_IN_PARENT)
            layerMainSplash.addView(linearLayout, params)
            @Suppress("SpellCheckingInspection")
//            layerMainSplash.setBackgroundColor(Color.parseColor("#22ffffff"))
            btnRegister.setOnClickListener { showRegister() }
        }
    }

    private fun register() {
        val linearLayout = LinearLayout(context)
        linearLayout.orientation = LinearLayout.VERTICAL
        linearLayout.gravity = Gravity.CENTER
        layerRegister.addView(linearLayout, ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))


        val params1 = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, screenHeight / 6)
        val tvTitle = TextView(context)
        params1.bottomMargin = 40
        tvTitle.text = context.getString(R.string.register)

        tvTitle.textSize = 22f
        tvTitle.gravity = Gravity.CENTER
        tvTitle.setTypeface(null, Typeface.BOLD)
        tvTitle.setTextColor(Color.parseColor("#2D8469"))

        linearLayout.addView(tvTitle, params1)

        val params2 = LinearLayout.LayoutParams((screenWidth / 1.5).toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)
        params2.setMargins(0, 15, 0, 15)
        val edtUser = EditText(context)
        edtUser.setPadding(9, 9, 9, 9)

        val textUser: String = if (typeUsername == TYPE_INPUT_USERNAME_MOBILE_EMAIL_USERNAME
                || typeUsername == TYPE_INPUT_USERNAME_MOBILE_EMAIL) {
            context.getString(R.string.email_or_mobile)
        } else if (typeUsername == TYPE_INPUT_USERNAME_MOBILE
                || typeUsername == TYPE_INPUT_USERNAME_USERNAME_MOBILE) {
            edtUser.filters += InputFilter.LengthFilter(11)
            edtUser.inputType = InputType.TYPE_CLASS_NUMBER
            context.getString(R.string.mobile)
        } else if (typeUsername == TYPE_INPUT_USERNAME_USERNAME
                || typeUsername == TYPE_INPUT_USERNAME_EMAIL
                || typeUsername == TYPE_INPUT_USERNAME_USERNAME_EMAIL) {
            context.getString(R.string.email)
        } else {
            customTextInputUsername
        }
        edtUser.hint = textUser
        edtUser.gravity = Gravity.CENTER
        edtUser.textSize = 18f
        edtUser.setBackgroundResource(R.drawable.bg_edt_login)

        edtUser.nextFocusDownId = R.id.edt_pass_register
        edtUser.nextFocusForwardId = R.id.edt_pass_register
        edtUser.nextFocusRightId = R.id.edt_pass_register

        if (typeUsername == TYPE_INPUT_USERNAME_MOBILE) {
            edtUser.filters += InputFilter.LengthFilter(11)
            edtUser.inputType = InputType.TYPE_CLASS_NUMBER
        } else
            edtUser.inputType = EditorInfo.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
        linearLayout.addView(edtUser, params2)

        val edtPassword = EditText(context)
        edtPassword.gravity = Gravity.CENTER
        edtPassword.setPadding(7, 7, 7, 7)
        edtPassword.hint = context.getString(R.string.password)
        edtPassword.id = R.id.edt_pass_register
        edtPassword.inputType = InputType.TYPE_CLASS_TEXT or
                InputType.TYPE_TEXT_VARIATION_PASSWORD
        edtPassword.setBackgroundResource(R.drawable.bg_edt_login)


        edtPassword.nextFocusDownId = R.id.edt_pass_re_register
        edtPassword.nextFocusForwardId = R.id.edt_pass_re_register
        edtPassword.nextFocusRightId = R.id.edt_pass_re_register

        linearLayout.addView(edtPassword, params2)

        edtPassword2.gravity = Gravity.CENTER
        edtPassword2.setPadding(7, 7, 7, 7)
        edtPassword2.hint = context.getString(R.string.re_password)
        edtPassword2.id = R.id.edt_pass_re_register

        edtPassword2.inputType = InputType.TYPE_CLASS_TEXT or
                InputType.TYPE_TEXT_VARIATION_PASSWORD
        edtPassword2.setBackgroundResource(R.drawable.bg_edt_login)

        if (layoutMore.childCount <= 0) {
            edtPassword2.nextFocusDownId = R.id.btn_register
            edtPassword2.nextFocusForwardId = R.id.btn_register
            edtPassword2.nextFocusRightId = R.id.btn_register
        }

        linearLayout.addView(edtPassword2, params2)

        val params3 = LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
        val params4 = LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
        //  params3.rightMargin = 50
        params3.topMargin = 25
        params3.addRule(ALIGN_PARENT_LEFT)
        params4.addRule(ALIGN_BOTTOM, R.id.btn_register)
        params4.addRule(ALIGN_PARENT_TOP)
        params4.topMargin = 100
        params4.addRule(ALIGN_PARENT_RIGHT)
        val btnRegister = Button(context)
        btnRegister.setBackgroundResource(R.drawable.bg_btn_login)
        btnRegister.text = context.getString(R.string.register)

        btnRegister.id = R.id.btn_register
        btnRegister.setTextColor(Color.WHITE)
        btnRegister.setOnClickListener { register(edtUser, edtPassword, edtPassword2) }
        linearLayout.addView(layoutMore)
        linearLayout.addView(btnRegister, params3)

        val btnLogin = TextView(context)
        val spanText = SpannableString(context.getString(R.string.have_you_already_registered))
        btnLogin.text = spanText
        TextViewCompat.setTextAppearance(btnLogin,
                R.style.AudioFileInfoOverlayText)
        btnLogin.gravity = Gravity.CENTER
        btnLogin.setOnClickListener { showLogin() }
        linearLayout.addView(btnLogin, params4)
        btnSkip.visibility = if (typeLoginView == TYPE_WITH_SKIP || typeLoginView == TYPE_WITH_REGISTER_AND_SKIP)
            View.VISIBLE else View.INVISIBLE
    }

    private fun register(edt_username: EditText
                         , edt_password: EditText
                         , edt_password_re: EditText) {
        val username = edt_username.text.toString()
        val password = edt_password.text.toString()
        val passwordRe = edt_password_re.text.toString()
        if (username == "") {
            edt_username.error = context.getString(R.string.this_section_can_not_be_empty)
            return
        }
        val isMobile = isInteger(username)
        val isEmail = !isMobile && username.contains("@")

        if ((typeUsername == TYPE_INPUT_USERNAME_MOBILE_EMAIL_USERNAME
                        || typeUsername == TYPE_INPUT_USERNAME_MOBILE
                        || typeUsername == TYPE_INPUT_USERNAME_MOBILE_EMAIL)
                && isMobile
        ) {

            if (username.isNotEmpty() && username[0] != '0') {
                edt_username.error = context.getString(R.string.mobile_must_by_zero)
                return
            }
            if (username.length < 11) {
                edt_username.error = context.getString(R.string.mobile_is_short)
                return
            }
            if (username.length > 11) {
                edt_username.error = context.getString(R.string.mobile_invalid)
                return
            }

        } else if ((typeUsername == TYPE_INPUT_USERNAME_MOBILE_EMAIL_USERNAME
                        || typeUsername == TYPE_INPUT_USERNAME_EMAIL
                        || typeUsername == TYPE_INPUT_USERNAME_MOBILE_EMAIL) && isEmail) {
            if (username.length < 7) {
                edt_username.error = context.getString(R.string.email_is_short)
                return
            }
        } else if (!isMobile && !isEmail) {
            edt_username.error = context.getString(R.string.email_invalid)
            return
        } else {
            edt_username.error = context.getString(R.string.username_invalid)
            return
        }
        if (password == "") {
            edt_password.error = context.getString(R.string.this_section_can_not_be_empty)
            return
        }
        if (password.length < 8) {
            edt_password.error = context.getString(R.string.password_is_short)
            return
        }
        if (password.length > 32) {
            edt_password.error = context.getString(R.string.password_is_long)
            return
        }
        if (passwordRe == "") {
            edt_password_re.error = context.getString(R.string.this_section_can_not_be_empty)
            return
        }
        if (password != passwordRe) {
            edt_password_re.error = context.getString(R.string.password_does_not_match)
            return
        }
        if (onLoginChangeListener != null) {
            val arrayEditTexts = ArrayList<EditText>()
            for (i in 0 until layoutMore.childCount) {
                val view = layoutMore.getChildAt(i)
                if (view is EditText)
                    arrayEditTexts.add(view)
            }
            onLoginChangeListener!!.onRegistered(username, password, arrayEditTexts.toTypedArray())
        }
    }

    private fun skip() {
        if (onLoginChangeListener != null)
            onLoginChangeListener!!.onSkipped()
    }

    init {


        btnSkip.text = context.getString(R.string.skip)
        btnSkip.textSize = 18f
        btnSkip.setTypeface(null, Typeface.BOLD)
        btnSkip.setTextColor(Color.BLACK)
        btnSkip.setPadding(20, 20, 20, 20)
        btnSkip.gravity = Gravity.CENTER
        btnSkip.id = R.id.btn_skip
        val paramsButtonSkip = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        paramsButtonSkip.addRule(ALIGN_PARENT_BOTTOM)
        paramsButtonSkip.addRule(CENTER_HORIZONTAL)
        btnSkip.tag = Login::class.java
        addView(btnSkip, paramsButtonSkip)
        btnSkip.setOnClickListener { skip() }


        layoutMore.orientation = LinearLayout.VERTICAL
        layoutMore.gravity = Gravity.CENTER

        layerMainSplash = RelativeLayout(context)
        layerMainSplash.tag = Login::class.java
        addView(layerMainSplash, ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                , ViewGroup.LayoutParams.MATCH_PARENT))

        val paramsLayoutsMain = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                , ViewGroup.LayoutParams.MATCH_PARENT)
        val layerMain = RelativeLayout(context)
        layerMain.tag = Login::class.java

        layerLogin = RelativeLayout(context)
        svLogin.addView(layerLogin)
        val paramsScrollView1 = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                , ViewGroup.LayoutParams.WRAP_CONTENT)
        val paramsScrollView2 = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                , ViewGroup.LayoutParams.WRAP_CONTENT)
        paramsScrollView1.addRule(CENTER_IN_PARENT)
        paramsScrollView2.addRule(CENTER_IN_PARENT)
        layerMain.addView(svLogin, paramsScrollView1)
        svLogin.x = (-screenWidth).toFloat()

        layerRegister = RelativeLayout(context)

        svRegister.tag = Login::class.java

        svRegister.addView(layerRegister)
        paramsLayoutsMain.setMargins(0, 10, 0, 50)
        layerMain.addView(svRegister, paramsScrollView2)
//        setBackgroundColor(Color.parseColor("#55ffffff"))

        paramsLayoutsMain.addRule(ABOVE, R.id.btn_skip)
        addView(layerMain, paramsLayoutsMain)
        svRegister.x = (screenWidth).toFloat()

        if (attrs != null) run {
            val typedArray = context.theme.obtainStyledAttributes(
                    attrs,
                    R.styleable.Login,
                    0, 0)
            //Reading values from the XML layout
            try {
                typeLoginView = typedArray.getInt(R.styleable.Login_type_login_page, TYPE_WITH_REGISTER_AND_SKIP)
                typeUsername = typedArray.getInt(R.styleable.Login_type_username_field, TYPE_INPUT_USERNAME_MOBILE_EMAIL_USERNAME)

            } finally {
                typedArray.recycle()
            }
        }

        login()
        if (typeLoginView == TYPE_WITH_REGISTER
                || typeLoginView == TYPE_WITH_REGISTER_AND_SKIP) {
            main()
            register()
        } else {
            showLogin()
        }
    }

    /**
     * [OnLoginChangeListener] a listener for change login status
     */
    interface OnLoginChangeListener {
        /**
         * [onLogged] a [user]  username and [pass] password for Login input and run with click login button
         */
        fun onLogged(user: String, pass: String)

        /**
         * [onRegistered] a [user]  username and [pass] password for Register input and run with click register button
         */
        fun onRegistered(user: String, pass: String, args: Array<View>)

        /**
         * [onSkipped] clicked Skip button
         */
        fun onSkipped()
    }

    companion object {

        /**
         * all types for set input username
         */
        const val TYPE_INPUT_USERNAME_EMAIL = 0
        const val TYPE_INPUT_USERNAME_MOBILE = 1
        const val TYPE_INPUT_USERNAME_MOBILE_EMAIL = 5
        const val TYPE_INPUT_USERNAME_MOBILE_EMAIL_USERNAME = 6
        const val TYPE_INPUT_USERNAME_USERNAME = 2
        const val TYPE_INPUT_USERNAME_USERNAME_EMAIL = 3
        const val TYPE_INPUT_USERNAME_USERNAME_MOBILE = 4
        /**
         * all type for show page login
         * [TYPE_WITH_SKIP] with button skip
         * [TYPE_WITH_REGISTER] with page and button register
         * [TYPE_WITH_REGISTER_AND_SKIP] with both above
         */
        @Suppress("unused")
        const val TYPE_WITH_NONE = 0
        const val TYPE_WITH_REGISTER = 2
        const val TYPE_WITH_REGISTER_AND_SKIP = 3
        const val TYPE_WITH_SKIP = 1

    }
}