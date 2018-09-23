package org.noandish.library.loginpage

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
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
    /**
     *  [inputUsername] a [type]  for type input field username and access to
     *  enter a special character
     */
    public fun inputUsername(type: Int) {
        this.type_username = type
    }

    private var type_username = 5
    private var type_login_view = 3
    public var onLoginChangeListener: OnLoginChangeListener? = null
       set

    private var layer_login: RelativeLayout
    private var layer_register: RelativeLayout
    private var layer_main_splash: RelativeLayout
    private var custom_text_input_username = ""

    init {

        layer_main_splash = RelativeLayout(context)
        addView(layer_main_splash, ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                , ViewGroup.LayoutParams.MATCH_PARENT))

        layer_login = RelativeLayout(context)
        addView(layer_login, ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                , ViewGroup.LayoutParams.MATCH_PARENT))
        layer_login.x = (-Utils.screenWidth).toFloat()

        layer_register = RelativeLayout(context)
        addView(layer_register, ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                , ViewGroup.LayoutParams.MATCH_PARENT))
        layer_register.x = (Utils.screenWidth).toFloat()

        if (attrs != null) run {
            val typedArray = context.theme.obtainStyledAttributes(
                    attrs,
                    R.styleable.Login,
                    0, 0)
            //Reading values from the XML layout
            try {
                type_login_view = typedArray.getInt(R.styleable.Login_type_login_page, TYPE_WITH_REGISTER_AND_SKIP)
                type_username = typedArray.getInt(R.styleable.Login_type_username_field, TYPE_INNPUT_USERNAME_MOBILE_EMAIL_USERNAME)

            } finally {
                typedArray.recycle()
            }
        }

        login()
        if (type_login_view == TYPE_WITH_REGISTER
                || type_login_view == TYPE_WITH_REGISTER_AND_SKIP) {
            main()
            register()
        } else {
            showLogin()
        }
    }

    private fun main() {
        val linearLayout = LinearLayout(context)
        linearLayout.orientation = LinearLayout.VERTICAL
        linearLayout.gravity = Gravity.CENTER
        val params_btn = RelativeLayout.LayoutParams((Utils.screenWidth / 1.2).toInt(), RelativeLayout.LayoutParams.WRAP_CONTENT)
        params_btn.topMargin = 50
        params_btn.bottomMargin = 50
        val btn_login = Button(context)
        btn_login.text = context.getString(R.string.login)
        btn_login.setBackgroundResource(R.drawable.bg_btn_main_login)
        btn_login.setPadding(30, 0, 30, 0)
        linearLayout.addView(btn_login, params_btn)
        btn_login.setOnClickListener(object : OnClickListener {
            override fun onClick(v: View?) {
                showLogin()
            }
        })
        if (type_login_view == TYPE_WITH_REGISTER
                || type_login_view == TYPE_WITH_REGISTER_AND_SKIP) {
            val btn_register = Button(context)
            btn_register.text = context.getString(R.string.register)
            btn_register.setPadding(30, 0, 30, 0)
            btn_register.setBackgroundResource(R.drawable.bg_btn_main_login)
            linearLayout.addView(btn_register, params_btn)
            val params = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT)
            params.addRule(RelativeLayout.CENTER_IN_PARENT)
            layer_main_splash.addView(linearLayout, params)
            layer_main_splash.setBackgroundColor(Color.parseColor("#22ffffff"))
            btn_register.setOnClickListener(object : OnClickListener {
                override fun onClick(v: View?) {
                    showRegister()
                }
            })
        }
    }

    /**
     * [showLogin] a function for show page login
     */
    public fun showLogin() {
        layer_main_splash.animate().x(Utils.screenWidth.toFloat()).start()
        layer_register.animate().x(Utils.screenWidth.toFloat()).start()
        layer_login.animate().x(0f).start()
    }

    /**
     * [showRegister] a function for show page register
     * if [type_login_view] equals [TYPE_WITH_REGISTER_AND_SKIP] OR [TYPE_WITH_REGISTER]
     */
    public fun showRegister() {
        if (type_login_view == TYPE_WITH_REGISTER_AND_SKIP || type_login_view == TYPE_WITH_REGISTER) {
            layer_login.animate().x(Utils.screenWidth.toFloat()).start()
            layer_main_splash.animate().x(Utils.screenWidth.toFloat()).start()
            layer_register.animate().x(0f).start()
        }
    }

    private fun login() {

        val linearLayout = LinearLayout(context)
        linearLayout.orientation = LinearLayout.VERTICAL
        linearLayout.gravity = Gravity.CENTER
        layer_login.addView(linearLayout, ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))


        val params1 = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, Utils.screenHeight / 6)
        val tv_title = TextView(context)
        params1.bottomMargin = 40
        tv_title.text = context.getString(R.string.login)
        tv_title.textSize = 22f
        tv_title.gravity = Gravity.CENTER
        tv_title.setTypeface(null, Typeface.BOLD)
        tv_title.setTextColor(Color.parseColor("#2D8469"))

        linearLayout.addView(tv_title, params1)

        val params2 = LinearLayout.LayoutParams((Utils.screenWidth / 1.5).toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)
        params2.setMargins(0, 15, 0, 15)
        val edt_user = EditText(context)
        edt_user.setPadding(9, 9, 9, 9)
        edt_user.hint = context.getString(R.string.email_or_mobile)
        edt_user.gravity = Gravity.CENTER
        edt_user.textSize = 18f
        edt_user.setBackgroundResource(R.drawable.bg_edt_login)
        edt_user.inputType = EditorInfo.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
        linearLayout.addView(edt_user, params2)

        val edt_password = EditText(context)
        edt_password.gravity = Gravity.CENTER
        edt_password.setPadding(7, 7, 7, 7)
        edt_password.hint = context.getString(R.string.password)
        edt_password.inputType = EditorInfo.TYPE_TEXT_VARIATION_PASSWORD

        val text_user: String = if (type_username == TYPE_INNPUT_USERNAME_MOBILE_EMAIL_USERNAME
        ) {
            context.getString(R.string.username_or_mobile_or_email)
        } else if (type_username == TYPE_INNPUT_USERNAME_MOBILE_EMAIL) {
            context.getString(R.string.email_or_mobile)
        } else if (type_username == TYPE_INNPUT_USERNAME_MOBILE
                || type_username == TYPE_INNPUT_USERNAME_USERNAME_MOBILE) {
            context.getString(R.string.mobile)
        } else if (type_username == TYPE_INNPUT_USERNAME_USERNAME
                || type_username == TYPE_INNPUT_USERNAME_EMAIL
                || type_username == TYPE_INNPUT_USERNAME_USERNAME_EMAIL) {
            context.getString(R.string.email)
        } else {
            custom_text_input_username
        }
        edt_user.setHint(text_user)


        edt_password.setBackgroundResource(R.drawable.bg_edt_login)
        linearLayout.addView(edt_password, params2)

        edt_password.inputType = InputType.TYPE_CLASS_TEXT or
                InputType.TYPE_TEXT_VARIATION_PASSWORD

        val params3 = RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
        val params4 = RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
        //  params3.rightMargin = 50
        params3.topMargin = 25
        params3.addRule(RelativeLayout.ALIGN_PARENT_LEFT)
        params4.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.btn_login)
        params4.addRule(RelativeLayout.ALIGN_PARENT_TOP)
        params4.topMargin = 100
        params4.addRule(RelativeLayout.ALIGN_PARENT_RIGHT)
        val btn_login = Button(context)
        btn_login.setBackgroundResource(R.drawable.bg_btn_login)
        btn_login.text = context.getString(R.string.login)
        btn_login.id = R.id.btn_login
        btn_login.setTextColor(Color.WHITE)
        linearLayout.addView(btn_login, params3)
        btn_login.setOnClickListener(object : OnClickListener {
            override fun onClick(v: View?) {
                login(edt_user, edt_password)
            }

        })
        if (type_login_view == TYPE_WITH_REGISTER
                || type_login_view == TYPE_WITH_REGISTER_AND_SKIP) {
            val btn_register = TextView(context)
            val span_text = SpannableString(context.getString(R.string.register)
            )
            btn_register.text = span_text
            btn_register.setTextAppearance(context,
                    R.style.AudioFileInfoOverlayText);
            btn_register.gravity = Gravity.CENTER
            linearLayout.addView(btn_register, params4)
            btn_register.setOnClickListener(object : OnClickListener {
                override fun onClick(v: View?) {
                    showRegister()
                }
            })
        }
        if (type_login_view == TYPE_WITH_SKIP || type_login_view == TYPE_WITH_REGISTER_AND_SKIP) {
            val btn_skip = TextView(context)
            btn_skip.text = context.getString(R.string.skip)
            btn_skip.textSize = 18f
            btn_skip.setTypeface(null, Typeface.BOLD)
            btn_skip.setTextColor(Color.BLACK)
            btn_skip.setPadding(20, 20, 20, 20)
            btn_skip.gravity = Gravity.CENTER
            val params5 = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT)
            params5.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
            params5.addRule(RelativeLayout.CENTER_HORIZONTAL)
            layer_login.addView(btn_skip, params5)
            btn_skip.setOnClickListener(object : OnClickListener {
                override fun onClick(v: View?) {
                    skip()
                }
            })
        }
        layer_login.setBackgroundColor(Color.parseColor("#55ffffff"))

        if (type_login_view == TYPE_WITH_SKIP
                || type_login_view == TYPE_WITH_REGISTER_AND_SKIP) {
            val btn_skip = TextView(context)
            btn_skip.text = context.getString(R.string.skip)
            btn_skip.textSize = 18f
            btn_skip.setTypeface(null, Typeface.BOLD)
            btn_skip.setTextColor(Color.BLACK)
            btn_skip.setPadding(20, 20, 20, 20)
            btn_skip.gravity = Gravity.CENTER
            val params5 = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT)
            params5.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
            params5.addRule(RelativeLayout.CENTER_HORIZONTAL)
            btn_skip.setOnClickListener(object : OnClickListener {
                override fun onClick(v: View?) {
                    skip()
                }

            })
            layer_login.addView(btn_skip, params5)
        }
    }

    private fun register() {
        val linearLayout = LinearLayout(context)
        linearLayout.orientation = LinearLayout.VERTICAL
        linearLayout.gravity = Gravity.CENTER
        layer_register.addView(linearLayout, ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))


        val params1 = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, Utils.screenHeight / 6)
        val tv_title = TextView(context)
        params1.bottomMargin = 40
        tv_title.text = context.getString(R.string.register)

        tv_title.textSize = 22f
        tv_title.gravity = Gravity.CENTER
        tv_title.setTypeface(null, Typeface.BOLD)
        tv_title.setTextColor(Color.parseColor("#2D8469"))

        linearLayout.addView(tv_title, params1)

        val params2 = LinearLayout.LayoutParams((Utils.screenWidth / 1.5).toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)
        params2.setMargins(0, 15, 0, 15)
        val edt_user = EditText(context)
        edt_user.setPadding(9, 9, 9, 9)
        val text_user: String = if (type_username == TYPE_INNPUT_USERNAME_MOBILE_EMAIL_USERNAME
                || type_username == TYPE_INNPUT_USERNAME_MOBILE_EMAIL) {
            context.getString(R.string.email_or_mobile)
        } else if (type_username == TYPE_INNPUT_USERNAME_MOBILE
                || type_username == TYPE_INNPUT_USERNAME_USERNAME_MOBILE) {
            context.getString(R.string.mobile)
        } else if (type_username == TYPE_INNPUT_USERNAME_USERNAME
                || type_username == TYPE_INNPUT_USERNAME_EMAIL
                || type_username == TYPE_INNPUT_USERNAME_USERNAME_EMAIL) {
            context.getString(R.string.email)
        } else {
            custom_text_input_username
        }
        edt_user.setHint(text_user)
        edt_user.gravity = Gravity.CENTER
        edt_user.textSize = 18f
        edt_user.setBackgroundResource(R.drawable.bg_edt_login)
        edt_user.inputType = EditorInfo.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
        linearLayout.addView(edt_user, params2)

        val edt_password = EditText(context)
        edt_password.gravity = Gravity.CENTER
        edt_password.setPadding(7, 7, 7, 7)
        edt_password.setHint(context.getString(R.string.password)
        )
        edt_password.inputType = InputType.TYPE_CLASS_TEXT or
                InputType.TYPE_TEXT_VARIATION_PASSWORD
        edt_password.setBackgroundResource(R.drawable.bg_edt_login)
        linearLayout.addView(edt_password, params2)

        var edt_password2 = EditText(context)
        edt_password2.gravity = Gravity.CENTER
        edt_password2.setPadding(7, 7, 7, 7)
        edt_password2.hint = context.getString(R.string.re_password)

        edt_password2.inputType = InputType.TYPE_CLASS_TEXT or
                InputType.TYPE_TEXT_VARIATION_PASSWORD
        edt_password2.setBackgroundResource(R.drawable.bg_edt_login)
        linearLayout.addView(edt_password2, params2)

        val params3 = RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
        val params4 = RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
        //  params3.rightMargin = 50
        params3.topMargin = 25
        params3.addRule(RelativeLayout.ALIGN_PARENT_LEFT)
        params4.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.btn_login)
        params4.addRule(RelativeLayout.ALIGN_PARENT_TOP)
        params4.topMargin = 100
        params4.addRule(RelativeLayout.ALIGN_PARENT_RIGHT)
        val btn_register = Button(context)
        btn_register.setBackgroundResource(R.drawable.bg_btn_login)
        btn_register.text = context.getString(R.string.register)

        btn_register.id = R.id.btn_login
        btn_register.setTextColor(Color.WHITE)
        btn_register.setOnClickListener(object : OnClickListener {
            override fun onClick(v: View?) {
                register(edt_user, edt_password, edt_password2)
            }
        })
        linearLayout.addView(btn_register, params3)

        val btn_login = TextView(context)
        val span_text = SpannableString(context.getString(R.string.have_you_already_registered))
        btn_login.text = span_text
        btn_login.setTextAppearance(context,
                R.style.AudioFileInfoOverlayText);
        btn_login.gravity = Gravity.CENTER
        btn_login.setOnClickListener(object : OnClickListener {
            override fun onClick(v: View?) {
                showLogin()
            }

        })
        linearLayout.addView(btn_login, params4)
        if (type_login_view == TYPE_WITH_SKIP || type_login_view == TYPE_WITH_REGISTER_AND_SKIP) {
            val btn_skip = TextView(context)
            btn_skip.text = context.getString(R.string.skip)
            btn_skip.textSize = 18f
            btn_skip.setTypeface(null, Typeface.BOLD)
            btn_skip.setTextColor(Color.BLACK)
            btn_skip.setPadding(20, 20, 20, 20)
            btn_skip.gravity = Gravity.CENTER
            val params5 = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT)
            params5.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
            params5.addRule(RelativeLayout.CENTER_HORIZONTAL)
            btn_skip.setOnClickListener(object : OnClickListener {
                override fun onClick(v: View?) {
                    skip()
                }

            })
            layer_register.addView(btn_skip, params5)
        }
        layer_register.setBackgroundColor(Color.parseColor("#55ffffff"))
    }

    private fun register(edt_username: EditText
                         , edt_password: EditText
                         , edt_password_re: EditText) {
        val username = edt_username.text.toString()
        val password = edt_password.text.toString()
        val password_re = edt_password_re.text.toString()
        if (username.equals("")) {
            edt_username.setError(context.getString(R.string.this_section_can_not_be_empty))
            return
        }
        val isMobile = isInteger(username)
        val isEmail = !isMobile && username.contains("@")

        if ((type_username == TYPE_INNPUT_USERNAME_MOBILE_EMAIL_USERNAME
                        || type_username == TYPE_INNPUT_USERNAME_MOBILE
                        || type_username == TYPE_INNPUT_USERNAME_MOBILE_EMAIL)
                && isMobile
        ) {

            if (username.length > 0 && !username[0].equals('0')) {
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

        } else if ((type_username == TYPE_INNPUT_USERNAME_MOBILE_EMAIL_USERNAME
                        || type_username == TYPE_INNPUT_USERNAME_EMAIL
                        || type_username == TYPE_INNPUT_USERNAME_MOBILE_EMAIL) && isEmail) {
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
        if (password.equals("")) {
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
        if (password_re.equals("")) {
            edt_password_re.error = context.getString(R.string.this_section_can_not_be_empty)
            return
        }
        if (!password.equals(password_re)) {
            edt_password_re.error = context.getString(R.string.password_does_not_match)
            return
        }
        if (onLoginChangeListener != null)
            onLoginChangeListener!!.onRegistered(username, password)
    }

    private fun login(edt_username: EditText, edt_password: EditText) {
        val username = edt_username.text.toString()
        val password = edt_password.text.toString()
        if (username.equals("")) {
            edt_username.setError(context.getString(R.string.this_section_can_not_be_empty))
            return
        }
        val isMobile = isInteger(username)
        val isEmail = !isMobile && username.contains("@")

        if ((type_username == TYPE_INNPUT_USERNAME_MOBILE_EMAIL_USERNAME
                        || type_username == TYPE_INNPUT_USERNAME_MOBILE
                        || type_username == TYPE_INNPUT_USERNAME_MOBILE_EMAIL)
                && isMobile
        ) {

            if (username.length > 0 && !username[0].equals('0')) {
                edt_username.error = context.getString(R.string.mobile_must_by_zero)
                return
            }
            if (username.length < 11) {
                edt_username.error = context.getString(R.string.mobile_is_short)
                return
            }
            if (username.length > 11) {
                edt_username.setError(context.getString(R.string.mobile_invalid))
                return
            }

        } else if ((type_username == TYPE_INNPUT_USERNAME_MOBILE_EMAIL_USERNAME
                        || type_username == TYPE_INNPUT_USERNAME_EMAIL
                        || type_username == TYPE_INNPUT_USERNAME_MOBILE_EMAIL) && isEmail) {
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
        if (password.equals("")) {
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
            onLoginChangeListener!!.onLogined(username, password)
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
            if (str.length === 1) {
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

    private fun skip() {
        if (onLoginChangeListener != null)
            onLoginChangeListener!!.onSkiped()
    }

    companion object {

        /**
         * [OnLoginChangeListener] a listener for change login status
         */
        interface OnLoginChangeListener {
            /**
             * [onLogined] a [user]  username and [pass] password for Login input and run with click login button
             */
            fun onLogined(user: String, pass: String)

            /**
             * [onRegistered] a [user]  username and [pass] password for Register input and run with click register button
             */
            fun onRegistered(user: String, pass: String)

            /**
             * [onSkiped] clicked Skip button
             */
            fun onSkiped()
        }

        /**
         * all type for show page login
         * [TYPE_WITH_SKIP] with button skip
         * [TYPE_WITH_REGISTER] with page and button register
         * [TYPE_WITH_REGISTER_AND_SKIP] with both above
         */
        public val TYPE_WITH_NONE = 0
        public val TYPE_WITH_SKIP = 1
        public val TYPE_WITH_REGISTER = 2
        public val TYPE_WITH_REGISTER_AND_SKIP = 3
        /**
         * all types for set input username
         */
        public val TYPE_INNPUT_USERNAME_EMAIL = 0
        public val TYPE_INNPUT_USERNAME_MOBILE = 1
        public val TYPE_INNPUT_USERNAME_USERNAME = 2
        public val TYPE_INNPUT_USERNAME_USERNAME_EMAIL = 3
        public val TYPE_INNPUT_USERNAME_USERNAME_MOBILE = 4
        public val TYPE_INNPUT_USERNAME_MOBILE_EMAIL = 5
        public val TYPE_INNPUT_USERNAME_MOBILE_EMAIL_USERNAME = 6

    }
}