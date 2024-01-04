package com.dailyapps.entity


data class ExamQuestion(

	val examItem: ExamItem? = null,

	val createdAt: Int? = null,

	val ujianId: String? = null,

	val deletedAt: Int? = null,

	val jenisSoal: String? = null,

	val kunciJawaban: String? = null,

	val opsiB: String? = null,

	val opsiC: String? = null,

	val imagesSoal: String? = null,

	val opsiA: String? = null,

	val updatedAt: Int? = null,

	val opsiD: String? = null,

	val opsiE: String? = null,

	val id: Int? = null,

	val bobotNilai: String? = null,

	val textSoal: String? = null,

	val jawabanObjektif: String? = null,

	val jawabanEssay: String? = null,
)

val ExamQuestion.objectiveOptions: List<ObjectiveExamQuestion>
	get() {
		val optionA = ObjectiveExamQuestion(
			option = "A",
			answer = opsiA ?: "",
			soalId = id ?: 0
		)
		val optionB = ObjectiveExamQuestion(
			option = "B",
			answer = opsiB,
			soalId = id ?: 0
		)
		val optionC = ObjectiveExamQuestion(
			option = "C",
			answer = opsiC,
			soalId = id ?: 0
		)
		val optionD = ObjectiveExamQuestion(
			option = "D",
			answer = opsiD,
			soalId = id ?: 0
		)
		val optionE = ObjectiveExamQuestion(
			option = "E",
			answer = opsiE,
			soalId = id ?: 0
		)
		return listOf(
			optionA,
			optionB,
			optionC,
			optionD,
			optionE
		).filter { it.answer != null }
	}

data class ObjectiveExamQuestion(
	val option: String,
	val answer: String?,
	val selected: Boolean = false,
	val soalId: Int
)

data class ExamItem(

	val namaNilaiId: String? = null,

	val isStart: String? = null,

	val ujianStart: String? = null,

	val isActive: String? = null,

	val jenisUjian: String? = null,

	val createdAt: Int? = null,

	val matpelId: String? = null,

	val guruId: String? = null,

	val deletedAt: Int? = null,

	val token: String? = null,

	val ujianEnd: String? = null,

	val expiredDuration: String? = null,

	val updatedAt: Int? = null,

	val tahunAjaranSemesterId: String? = null,

	val isFinish: String? = null,

	val id: Int? = null
)
