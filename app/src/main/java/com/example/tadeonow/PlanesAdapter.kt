import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tadeonow.R

// Clase Plan para representar un objeto de plan
data class Plan(val nombre: String, val horario: String, val ubicacion: String, val asistentes: Int)

// Adaptador para mostrar los datos en el RecyclerView
class PlanesAdapter : RecyclerView.Adapter<PlanesAdapter.PlanViewHolder>() {

    private var planesList = listOf<Plan>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_plan, parent, false)
        return PlanViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlanViewHolder, position: Int) {
        val plan = planesList[position]
        holder.bind(plan)
    }

    override fun getItemCount(): Int {
        return planesList.size
    }

    fun setPlanes(planes: List<Plan>) {
        this.planesList = planes
        notifyDataSetChanged()
    }

    class PlanViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(plan: Plan) {
            itemView.apply {
                findViewById<TextView>(R.id.tituloPlan1).text = plan.nombre
                findViewById<TextView>(R.id.dato1_plan1).text = "HORARIO: ${plan.horario}"
                findViewById<TextView>(R.id.dato2_plan1).text = "UBICACIÓN: ${plan.ubicacion}"
                findViewById<TextView>(R.id.dato3_plan1).text = "ASISTENTES: ${plan.asistentes}"
            }
        }
    }
}

