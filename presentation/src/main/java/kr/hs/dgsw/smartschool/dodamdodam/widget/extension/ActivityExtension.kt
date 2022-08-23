package kr.hs.dgsw.smartschool.dodamdodam.widget.extension

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.telephony.TelephonyManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

fun AppCompatActivity.shortToast(message: String?) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun AppCompatActivity.shortToast(message: Int) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun AppCompatActivity.longToast(message: String?) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun AppCompatActivity.longToast(message: Int) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun AppCompatActivity.startActivity(activity: Class<*>) {
    startActivity(Intent(applicationContext, activity).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP))
}

fun AppCompatActivity.startActivityWithFinish(activity: Class<*>) {
    startActivityWithFinish(Intent(applicationContext, activity).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP))
}

fun AppCompatActivity.startActivityWithFinish(intent: Intent) {
    startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP))
    this.finish()
}

fun AppCompatActivity.startActivityWithFinishAll(activity: Class<*>) {
    val intent = Intent(applicationContext, activity)
    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
    startActivity(intent)
    this.finishAffinity()
}

fun AppCompatActivity.startActivitiesWithFinish(vararg activity: Class<*>) {
    val intents: ArrayList<Intent> = ArrayList()
    for (clazz in activity) {
        val intent = Intent(applicationContext, clazz)
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        intents.add(intent)
    }
    startActivities(intents.toArray(arrayOf<Intent?>()))
    this.finish()
}

@SuppressLint("HardwareIds", "MissingPermission")
fun Context.getPhoneNumber(): String {
    val phoneNumber: String

    val mgr = this.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
    phoneNumber = try {
        val tempPhoneNumber = mgr.line1Number
        tempPhoneNumber.replace("+82", "0")
    } catch (e: Exception) {
        ""
    }

    return phoneNumber
}

inline fun <reified T : ViewModel> AppCompatActivity.getViewModel(factory: ViewModelProvider.Factory): T =
    ViewModelProvider(this, factory)[T::class.java]

inline fun <reified T : ViewModel> AppCompatActivity.getViewModel(): T =
    ViewModelProvider(this)[T::class.java]
